package com.ivan.weather.station.core.anomaly;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.ivan.weather.station.core.initializator.PowerPlugRemoteControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.mail.Email;
import com.ivan.weather.station.core.mail.EmailClient;
import com.ivan.weather.station.persistence.entity.Raspberry;

@Component
public class AnomalyDetectionRuleDetector {

    private final EmailClient emailClient;
    private final PowerPlugRemoteControl powerPlugRemoteControl;

    @Autowired
    public AnomalyDetectionRuleDetector(EmailClient emailClient, PowerPlugRemoteControl powerPlugRemoteControl) {
        this.emailClient = emailClient;
        this.powerPlugRemoteControl = powerPlugRemoteControl;
    }

    public void detectForAnomalies(MeasurementServiceModel measurement, Raspberry raspberry, String email) {
        for (AnomalyDetectionRuleServiceModel anomalyDetectionRule : parseAnomalyDetectionRules(raspberry)) {
            if (anomalyDetectionRule.isOutOfConstraint(measurement)) {
                AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = anomalyDetectionRule.toResponseModel();
                emailClient.sendAsync(getEmail(email, anomalyDetectionRuleResponseModel.getType(), measurement.getRaspberry()
                                                                                                              .getName()));

                // TODO: Stop/start power plugs in case of anomaly
            }
        }
    }

    private List<AnomalyDetectionRuleServiceModel> parseAnomalyDetectionRules(Raspberry raspberry) {
        return raspberry.getAnomalyDetectionRules()
                        .stream()
                        .map(anomalyDetectionRule -> AnomalyDetectionRuleType.from(anomalyDetectionRule.getType())
                                                                             .getRuleServiceParser(anomalyDetectionRule)
                                                                             .parse())
                        .collect(Collectors.toList());
    }

    private Email getEmail(String email, String type, String raspberryName) {
        return new Email.Builder().setRecipient(email)
                                  .setTitle("Anomaly Detected!")
                                  .setContent(MessageFormat.format("Anomaly of type: \"{0}\" detected for raspberry: \"{1}\"", type,
                                                                   raspberryName))
                                  .build();
    }

}

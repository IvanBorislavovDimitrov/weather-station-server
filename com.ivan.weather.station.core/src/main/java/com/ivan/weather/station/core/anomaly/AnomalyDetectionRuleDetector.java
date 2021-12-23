package com.ivan.weather.station.core.anomaly;

import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.mail.Email;
import com.ivan.weather.station.core.mail.EmailClient;

@Component
public class AnomalyDetectionRuleDetector {

    private final EmailClient emailClient;

    @Autowired
    public AnomalyDetectionRuleDetector(EmailClient emailClient) {
        this.emailClient = emailClient;
    }

    public void detectForAnomalies(MeasurementServiceModel measurement, List<AnomalyDetectionRuleServiceModel> anomalyDetectionRules,
                                   String email) {
        for (AnomalyDetectionRuleServiceModel anomalyDetectionRule : anomalyDetectionRules) {
            if (anomalyDetectionRule.isOutOfConstraint(measurement)) {
                AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = anomalyDetectionRule.toResponseModel();
                emailClient.sendAsync(getEmail(email, anomalyDetectionRuleResponseModel.getType(), measurement.getRaspberry()
                                                                                                                 .getName()));

            }
        }
    }

    private Email getEmail(String email, String type, String raspberryName) {
        return new Email.Builder().setRecipient(email)
                                  .setTitle("Anomaly Detected!")
                                  .setContent(MessageFormat.format("Anomaly of type: \"{0}\" detected for raspberry: \"{1}\"", type,
                                                                   raspberryName))
                                  .build();
    }

}

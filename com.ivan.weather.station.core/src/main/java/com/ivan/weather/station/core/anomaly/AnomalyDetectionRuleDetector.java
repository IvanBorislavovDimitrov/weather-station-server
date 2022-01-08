package com.ivan.weather.station.core.anomaly;

import java.text.MessageFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.domain.model.MeasurementServiceModel;
import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;
import com.ivan.weather.station.core.initializator.PowerPlugRemoteControl;
import com.ivan.weather.station.core.mail.Email;
import com.ivan.weather.station.core.mail.EmailClient;
import com.ivan.weather.station.persistence.entity.Raspberry;

@Component
public class AnomalyDetectionRuleDetector {

    private final EmailClient emailClient;
    private final PowerPlugRemoteControl powerPlugRemoteControl;
    private final ModelMapper modelMapper;

    @Autowired
    public AnomalyDetectionRuleDetector(EmailClient emailClient, PowerPlugRemoteControl powerPlugRemoteControl, ModelMapper modelMapper) {
        this.emailClient = emailClient;
        this.powerPlugRemoteControl = powerPlugRemoteControl;
        this.modelMapper = modelMapper;
    }

    public void detectForAnomalies(MeasurementServiceModel measurement, Raspberry raspberry) {
        for (AnomalyDetectionRuleServiceModel anomalyDetectionRule : parseAnomalyDetectionRules(raspberry)) {
            if (anomalyDetectionRule.isOutOfUpperConstraint(measurement)) {
                executeControl(raspberry, anomalyDetectionRule, powerPlug -> powerPlug.getActionOnAboveAnomaly()
                                                                                      .getActionValue());
            }
            if (anomalyDetectionRule.isOutOfDownConstraint(measurement)) {
                executeControl(raspberry, anomalyDetectionRule, powerPlug -> powerPlug.getActionOnBelowAnomaly()
                                                                                      .getActionValue());
            }
        }
    }

    private void executeControl(Raspberry raspberry, AnomalyDetectionRuleServiceModel anomalyDetectionRule,
                                Function<PowerPlugServiceModel, String> anomalyActionGetter) {
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = anomalyDetectionRule.toResponseModel();
        List<PowerPlugServiceModel> powerPlugsForAnomaly = anomalyDetectionRule.getPlugsForAnomaly(parsePowerPlugs(raspberry));
        for (PowerPlugServiceModel powerPlugServiceModel : powerPlugsForAnomaly) {
            String actionValue = anomalyActionGetter.apply(powerPlugServiceModel);
            powerPlugRemoteControl.executeAction(powerPlugServiceModel.getRoute(), actionValue);
        }
        emailClient.sendAsync(getEmail(raspberry.getOwner()
                                                .getEmail(),
                                       anomalyDetectionRuleResponseModel.getType(), raspberry.getName()));
    }

    private List<AnomalyDetectionRuleServiceModel> parseAnomalyDetectionRules(Raspberry raspberry) {
        return raspberry.getAnomalyDetectionRules()
                        .stream()
                        .map(anomalyDetectionRule -> AnomalyDetectionRuleType.from(anomalyDetectionRule.getType())
                                                                             .getRuleServiceParser(anomalyDetectionRule)
                                                                             .parse())
                        .collect(Collectors.toList());
    }

    private List<PowerPlugServiceModel> parsePowerPlugs(Raspberry raspberry) {
        return raspberry.getPowerPlugs()
                        .stream()
                        .map(powerPlug -> modelMapper.map(powerPlug, PowerPlugServiceModel.class))
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

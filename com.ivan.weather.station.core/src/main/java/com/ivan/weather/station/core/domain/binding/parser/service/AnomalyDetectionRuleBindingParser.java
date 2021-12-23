package com.ivan.weather.station.core.domain.binding.parser.service;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.core.domain.model.RaspberryServiceModel;

public abstract class AnomalyDetectionRuleBindingParser<T extends AnomalyDetectionRuleServiceModel> {

    private final AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel;

    protected AnomalyDetectionRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        this.anomalyDetectionRuleBindingModel = anomalyDetectionRuleBindingModel;
    }

    public T parse() {
        T anomalyDetectionRuleServiceModel = getModel();
        anomalyDetectionRuleServiceModel.setRuleBelowActivated(anomalyDetectionRuleBindingModel.isRuleBelowActivated());
        anomalyDetectionRuleServiceModel.setRuleAboveActivated(anomalyDetectionRuleBindingModel.isRuleAboveActivated());
        RaspberryServiceModel raspberryServiceModel = new RaspberryServiceModel();
        raspberryServiceModel.setId(anomalyDetectionRuleBindingModel.getRaspberryId());
        anomalyDetectionRuleServiceModel.setRaspberry(raspberryServiceModel);
        return anomalyDetectionRuleServiceModel;
    }

    protected abstract T getModel();

    public AnomalyDetectionRuleBindingModel getAnomalyDetectionRuleBindingRequest() {
        return anomalyDetectionRuleBindingModel;
    }
}

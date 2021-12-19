package com.ivan.weather.station.core.domain.binding.parser;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingRequest;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;

public abstract class AnomalyDetectionRuleParser<T extends AnomalyDetectionRuleServiceModel> {

    private final AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest;

    protected AnomalyDetectionRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
        this.anomalyDetectionRuleBindingRequest = anomalyDetectionRuleBindingRequest;
    }

    protected T parse() {
        T anomalyDetectionRuleServiceModel = getModel();
        anomalyDetectionRuleServiceModel.setRuleBelowActivated(anomalyDetectionRuleBindingRequest.isRuleBelowActivated());
        anomalyDetectionRuleServiceModel.setRuleAboveActivated(anomalyDetectionRuleBindingRequest.isRuleAboveActivated());
        return anomalyDetectionRuleServiceModel;
    }

    protected abstract T getModel();

    public AnomalyDetectionRuleBindingRequest getAnomalyDetectionRuleBindingRequest() {
        return anomalyDetectionRuleBindingRequest;
    }
}

package com.ivan.weather.station.core.domain.binding.parser;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.AnomalyDetectionRuleServiceModel;

public abstract class AnomalyDetectionRuleParser<T extends AnomalyDetectionRuleServiceModel> {

    private final AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel;

    protected AnomalyDetectionRuleParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        this.anomalyDetectionRuleBindingModel = anomalyDetectionRuleBindingModel;
    }

    protected T parse() {
        T anomalyDetectionRuleServiceModel = getModel();
        anomalyDetectionRuleServiceModel.setRuleBelowActivated(anomalyDetectionRuleBindingModel.isRuleBelowActivated());
        anomalyDetectionRuleServiceModel.setRuleAboveActivated(anomalyDetectionRuleBindingModel.isRuleAboveActivated());
        return anomalyDetectionRuleServiceModel;
    }

    protected abstract T getModel();

    public AnomalyDetectionRuleBindingModel getAnomalyDetectionRuleBindingRequest() {
        return anomalyDetectionRuleBindingModel;
    }
}

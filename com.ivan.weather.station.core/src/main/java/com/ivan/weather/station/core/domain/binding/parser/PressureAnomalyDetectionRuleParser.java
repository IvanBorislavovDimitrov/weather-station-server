package com.ivan.weather.station.core.domain.binding.parser;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingRequest;
import com.ivan.weather.station.core.domain.model.PressureAnomalyDetectionRuleServiceModel;

public class PressureAnomalyDetectionRuleParser extends AnomalyDetectionRuleParser<PressureAnomalyDetectionRuleServiceModel> {

    public PressureAnomalyDetectionRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
        super(anomalyDetectionRuleBindingRequest);
    }

    @Override
    protected PressureAnomalyDetectionRuleServiceModel parse() {
        PressureAnomalyDetectionRuleServiceModel pressureAnomalyDetectionRuleServiceModel = super.parse();
        pressureAnomalyDetectionRuleServiceModel.setPressureBelowValue(getAnomalyDetectionRuleBindingRequest().getValueBelow());
        pressureAnomalyDetectionRuleServiceModel.setPressureAboveValue(getAnomalyDetectionRuleBindingRequest().getValueAbove());
        return pressureAnomalyDetectionRuleServiceModel;
    }

    @Override
    public PressureAnomalyDetectionRuleServiceModel getModel() {
        return new PressureAnomalyDetectionRuleServiceModel();
    }
}

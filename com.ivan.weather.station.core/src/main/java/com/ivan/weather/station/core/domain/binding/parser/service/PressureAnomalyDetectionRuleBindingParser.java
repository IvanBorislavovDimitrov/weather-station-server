package com.ivan.weather.station.core.domain.binding.parser.service;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.PressureAnomalyDetectionRuleServiceModel;

public class PressureAnomalyDetectionRuleBindingParser extends AnomalyDetectionRuleBindingParser<PressureAnomalyDetectionRuleServiceModel> {

    public PressureAnomalyDetectionRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        super(anomalyDetectionRuleBindingModel);
    }

    @Override
    public PressureAnomalyDetectionRuleServiceModel parse() {
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

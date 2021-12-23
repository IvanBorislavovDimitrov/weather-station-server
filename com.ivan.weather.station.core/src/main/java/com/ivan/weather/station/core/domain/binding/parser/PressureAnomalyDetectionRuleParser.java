package com.ivan.weather.station.core.domain.binding.parser;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.PressureAnomalyDetectionRuleServiceModel;

public class PressureAnomalyDetectionRuleParser extends AnomalyDetectionRuleParser<PressureAnomalyDetectionRuleServiceModel> {

    public PressureAnomalyDetectionRuleParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
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

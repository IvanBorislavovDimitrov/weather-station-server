package com.ivan.weather.station.core.domain.binding.parser.entity;

import com.ivan.weather.station.core.domain.model.PressureAnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.PressureAnomalyDetectionRule;

public class PressureAnomalyDetectionRuleServiceParser extends AnomalyDetectionRuleServiceParser<PressureAnomalyDetectionRuleServiceModel> {

    public PressureAnomalyDetectionRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
        super(anomalyDetectionRule);
    }

    @Override
    public PressureAnomalyDetectionRuleServiceModel parse() {
        PressureAnomalyDetectionRuleServiceModel model = super.parse();
        PressureAnomalyDetectionRule pressureAnomalyDetectionRule = (PressureAnomalyDetectionRule) getAnomalyDetectionRule();
        model.setPressureAboveValue(pressureAnomalyDetectionRule.getPressureAboveValue());
        model.setPressureBelowValue(pressureAnomalyDetectionRule.getPressureBelowValue());
        return model;
    }

    @Override
    protected PressureAnomalyDetectionRuleServiceModel getModel() {
        return new PressureAnomalyDetectionRuleServiceModel();
    }
}

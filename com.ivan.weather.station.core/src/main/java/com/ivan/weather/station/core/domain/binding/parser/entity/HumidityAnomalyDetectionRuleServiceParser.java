package com.ivan.weather.station.core.domain.binding.parser.entity;

import com.ivan.weather.station.core.domain.model.HumidityAnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.HumidityAnomalyDetectionRule;

public class HumidityAnomalyDetectionRuleServiceParser extends AnomalyDetectionRuleServiceParser<HumidityAnomalyDetectionRuleServiceModel> {

    public HumidityAnomalyDetectionRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
        super(anomalyDetectionRule);
    }

    @Override
    public HumidityAnomalyDetectionRuleServiceModel parse() {
        HumidityAnomalyDetectionRuleServiceModel model = super.parse();
        HumidityAnomalyDetectionRule humidityAnomalyDetectionRule = (HumidityAnomalyDetectionRule) getAnomalyDetectionRule();
        model.setHumidityAboveValue(humidityAnomalyDetectionRule.getHumidityAboveValue());
        model.setHumidityBelowValue(humidityAnomalyDetectionRule.getHumidityBelowValue());
        return model;
    }

    @Override
    protected HumidityAnomalyDetectionRuleServiceModel getModel() {
        return new HumidityAnomalyDetectionRuleServiceModel();
    }
}

package com.ivan.weather.station.core.domain.binding.parser.entity;

import com.ivan.weather.station.core.domain.model.TemperatureAnomalyDetectionRuleServiceModel;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.TemperatureAnomalyDetectionRule;

public class TemperatureAnomalyDetectionRuleServiceParser
    extends AnomalyDetectionRuleServiceParser<TemperatureAnomalyDetectionRuleServiceModel> {

    public TemperatureAnomalyDetectionRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
        super(anomalyDetectionRule);
    }

    @Override
    public TemperatureAnomalyDetectionRuleServiceModel parse() {
        TemperatureAnomalyDetectionRuleServiceModel model = super.parse();
        TemperatureAnomalyDetectionRule temperatureAnomalyDetectionRule = (TemperatureAnomalyDetectionRule) getAnomalyDetectionRule();
        model.setTemperatureAboveValue(temperatureAnomalyDetectionRule.getTemperatureAboveValue());
        model.setTemperatureBelowValue(temperatureAnomalyDetectionRule.getTemperatureBelowValue());
        return model;
    }

    @Override
    protected TemperatureAnomalyDetectionRuleServiceModel getModel() {
        return new TemperatureAnomalyDetectionRuleServiceModel();
    }
}

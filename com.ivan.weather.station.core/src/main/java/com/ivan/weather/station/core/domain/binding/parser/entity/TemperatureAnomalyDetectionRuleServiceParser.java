package com.ivan.weather.station.core.domain.binding.parser.entity;

import com.ivan.weather.station.core.domain.model.TemperatureAnomalyDetectionRuleSericeModel;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.TemperatureAnomalyDetectionRule;

public class TemperatureAnomalyDetectionRuleServiceParser
    extends AnomalyDetectionRuleServiceParser<TemperatureAnomalyDetectionRuleSericeModel> {

    public TemperatureAnomalyDetectionRuleServiceParser(AnomalyDetectionRule anomalyDetectionRule) {
        super(anomalyDetectionRule);
    }

    @Override
    public TemperatureAnomalyDetectionRuleSericeModel parse() {
        TemperatureAnomalyDetectionRuleSericeModel model = super.parse();
        TemperatureAnomalyDetectionRule temperatureAnomalyDetectionRule = (TemperatureAnomalyDetectionRule) getAnomalyDetectionRule();
        model.setTemperatureAboveValue(temperatureAnomalyDetectionRule.getTemperatureAboveValue());
        model.setTemperatureBelowValue(temperatureAnomalyDetectionRule.getTemperatureBelowValue());
        return model;
    }

    @Override
    protected TemperatureAnomalyDetectionRuleSericeModel getModel() {
        return new TemperatureAnomalyDetectionRuleSericeModel();
    }
}

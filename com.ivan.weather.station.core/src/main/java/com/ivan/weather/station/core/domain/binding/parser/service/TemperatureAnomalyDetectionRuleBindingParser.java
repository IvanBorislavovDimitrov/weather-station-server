package com.ivan.weather.station.core.domain.binding.parser.service;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.TemperatureAnomalyDetectionRuleServiceModel;

public class TemperatureAnomalyDetectionRuleBindingParser
    extends AnomalyDetectionRuleBindingParser<TemperatureAnomalyDetectionRuleServiceModel> {

    public TemperatureAnomalyDetectionRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        super(anomalyDetectionRuleBindingModel);
    }

    @Override
    public TemperatureAnomalyDetectionRuleServiceModel parse() {
        TemperatureAnomalyDetectionRuleServiceModel temperatureAnomalyDetectionRuleServiceModel = super.parse();
        temperatureAnomalyDetectionRuleServiceModel.setTemperatureBelowValue(getAnomalyDetectionRuleBindingRequest().getValueBelow());
        temperatureAnomalyDetectionRuleServiceModel.setTemperatureAboveValue(getAnomalyDetectionRuleBindingRequest().getValueAbove());
        return temperatureAnomalyDetectionRuleServiceModel;
    }

    @Override
    protected TemperatureAnomalyDetectionRuleServiceModel getModel() {
        return new TemperatureAnomalyDetectionRuleServiceModel();
    }
}

package com.ivan.weather.station.core.domain.binding.parser;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingRequest;
import com.ivan.weather.station.core.domain.model.TemperatureAnomalyDetectionRuleSericeModel;

public class TemperatureAnomalyDetectionRuleParser extends AnomalyDetectionRuleParser<TemperatureAnomalyDetectionRuleSericeModel> {

    public TemperatureAnomalyDetectionRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
        super(anomalyDetectionRuleBindingRequest);
    }

    @Override
    public TemperatureAnomalyDetectionRuleSericeModel parse() {
        TemperatureAnomalyDetectionRuleSericeModel temperatureAnomalyDetectionRuleSericeModel = super.parse();
        temperatureAnomalyDetectionRuleSericeModel.setTemperatureBelowValue(getAnomalyDetectionRuleBindingRequest().getValueBelow());
        temperatureAnomalyDetectionRuleSericeModel.setTemperatureAboveValue(getAnomalyDetectionRuleBindingRequest().getValueAbove());
        return temperatureAnomalyDetectionRuleSericeModel;
    }

    @Override
    protected TemperatureAnomalyDetectionRuleSericeModel getModel() {
        return new TemperatureAnomalyDetectionRuleSericeModel();
    }
}

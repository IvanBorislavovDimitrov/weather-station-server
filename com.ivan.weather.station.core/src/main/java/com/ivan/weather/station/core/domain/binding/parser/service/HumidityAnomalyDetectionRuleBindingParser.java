package com.ivan.weather.station.core.domain.binding.parser.service;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingModel;
import com.ivan.weather.station.core.domain.model.HumidityAnomalyDetectionRuleServiceModel;

public class HumidityAnomalyDetectionRuleBindingParser extends AnomalyDetectionRuleBindingParser<HumidityAnomalyDetectionRuleServiceModel> {

    public HumidityAnomalyDetectionRuleBindingParser(AnomalyDetectionRuleBindingModel anomalyDetectionRuleBindingModel) {
        super(anomalyDetectionRuleBindingModel);
    }

    @Override
    public HumidityAnomalyDetectionRuleServiceModel parse() {
        HumidityAnomalyDetectionRuleServiceModel humidityAnomalyDetectionRuleServiceModel = super.parse();
        humidityAnomalyDetectionRuleServiceModel.setHumidityBelowValue(getAnomalyDetectionRuleBindingRequest().getValueBelow());
        humidityAnomalyDetectionRuleServiceModel.setHumidityAboveValue(getAnomalyDetectionRuleBindingRequest().getValueAbove());
        return humidityAnomalyDetectionRuleServiceModel;
    }

    @Override
    public HumidityAnomalyDetectionRuleServiceModel getModel() {
        return new HumidityAnomalyDetectionRuleServiceModel();
    }
}

package com.ivan.weather.station.core.domain.binding.parser;

import com.ivan.weather.station.core.domain.binding.request.AnomalyDetectionRuleBindingRequest;
import com.ivan.weather.station.core.domain.model.HumidityAnomalyDetectionRuleServiceModel;

public class HumidityAnomalyDetectionRuleParser extends AnomalyDetectionRuleParser<HumidityAnomalyDetectionRuleServiceModel> {

    public HumidityAnomalyDetectionRuleParser(AnomalyDetectionRuleBindingRequest anomalyDetectionRuleBindingRequest) {
        super(anomalyDetectionRuleBindingRequest);
    }

    @Override
    protected HumidityAnomalyDetectionRuleServiceModel parse() {
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

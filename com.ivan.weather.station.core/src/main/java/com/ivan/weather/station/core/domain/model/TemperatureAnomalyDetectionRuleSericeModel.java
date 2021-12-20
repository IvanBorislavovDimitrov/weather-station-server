package com.ivan.weather.station.core.domain.model;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;

public class TemperatureAnomalyDetectionRuleSericeModel extends AnomalyDetectionRuleServiceModel {

    private double temperatureBelowValue;
    private double temperatureAboveValue;

    public double getTemperatureBelowValue() {
        return temperatureBelowValue;
    }

    public void setTemperatureBelowValue(double temperatureBelowValue) {
        this.temperatureBelowValue = temperatureBelowValue;
    }

    public double getTemperatureAboveValue() {
        return temperatureAboveValue;
    }

    public void setTemperatureAboveValue(double temperatureAboveValue) {
        this.temperatureAboveValue = temperatureAboveValue;
    }

    @Override
    public AnomalyDetectionRuleResponseModel toResponseModel() {
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = super.toResponseModel();
        anomalyDetectionRuleResponseModel.setValueAbove(getTemperatureAboveValue());
        anomalyDetectionRuleResponseModel.setValueBelow(getTemperatureBelowValue());
        anomalyDetectionRuleResponseModel.setType(AnomalyDetectionRuleType.TEMPERATURE.getValue());
        return anomalyDetectionRuleResponseModel;
    }
}

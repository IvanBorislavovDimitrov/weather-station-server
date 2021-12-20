package com.ivan.weather.station.core.domain.model;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;

public class HumidityAnomalyDetectionRuleServiceModel extends AnomalyDetectionRuleServiceModel {

    private double humidityBelowValue;
    private double humidityAboveValue;

    public double getHumidityBelowValue() {
        return humidityBelowValue;
    }

    public void setHumidityBelowValue(double humidityBelowValue) {
        this.humidityBelowValue = humidityBelowValue;
    }

    public double getHumidityAboveValue() {
        return humidityAboveValue;
    }

    public void setHumidityAboveValue(double humidityAboveValue) {
        this.humidityAboveValue = humidityAboveValue;
    }

    @Override
    public AnomalyDetectionRuleResponseModel toResponseModel() {
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = super.toResponseModel();
        anomalyDetectionRuleResponseModel.setValueAbove(getHumidityAboveValue());
        anomalyDetectionRuleResponseModel.setValueBelow(getHumidityBelowValue());
        anomalyDetectionRuleResponseModel.setType(AnomalyDetectionRuleType.HUMIDITY.getValue());
        return anomalyDetectionRuleResponseModel;
    }

}

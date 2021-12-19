package com.ivan.weather.station.core.domain.model;

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
}

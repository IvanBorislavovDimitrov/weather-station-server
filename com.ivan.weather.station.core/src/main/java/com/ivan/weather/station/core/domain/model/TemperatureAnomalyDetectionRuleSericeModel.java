package com.ivan.weather.station.core.domain.model;

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
}

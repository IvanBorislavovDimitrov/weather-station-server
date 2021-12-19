package com.ivan.weather.station.core.domain.model;

public class PressureAnomalyDetectionRuleServiceModel extends AnomalyDetectionRuleServiceModel {

    private double pressureBelowValue;
    private double pressureAboveValue;

    public double getPressureBelowValue() {
        return pressureBelowValue;
    }

    public void setPressureBelowValue(double pressureBelowValue) {
        this.pressureBelowValue = pressureBelowValue;
    }

    public double getPressureAboveValue() {
        return pressureAboveValue;
    }

    public void setPressureAboveValue(double pressureAboveValue) {
        this.pressureAboveValue = pressureAboveValue;
    }
}

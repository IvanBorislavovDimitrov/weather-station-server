package com.ivan.weather.station.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pressure_anomaly_detection_rule")
public class PressureAnomalyDetectionRule extends AnomalyDetectionRule {

    @Column(name = "pressure_below_value")
    private double pressureBelowValue;
    @Column(name = "pressureAboveValue")
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

    @Override
    public double getBelowValue() {
        return getPressureBelowValue();
    }

    @Override
    public double getAboveValue() {
        return getPressureAboveValue();
    }
}

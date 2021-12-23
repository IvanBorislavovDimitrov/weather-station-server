package com.ivan.weather.station.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ivan.weather.station.persistence.constant.Constants;

@Entity
@Table(name = "pressure_anomaly_detection_rule")
public class PressureAnomalyDetectionRule extends AnomalyDetectionRule {

    @Column(name = "pressure_below_value")
    private double pressureBelowValue;
    @Column(name = "pressureAboveValue")
    private double pressureAboveValue;

    @Override
    public String getType() {
        return Constants.PRESSURE_TYPE;
    }

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

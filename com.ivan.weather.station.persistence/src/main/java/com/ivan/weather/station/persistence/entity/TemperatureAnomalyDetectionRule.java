package com.ivan.weather.station.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.ivan.weather.station.persistence.constant.Constants;

@Entity
@Table(name = "temperature_anomaly_detection_rules")
public class TemperatureAnomalyDetectionRule extends AnomalyDetectionRule {

    @Column(name = "temperature_below_value")
    private double temperatureBelowValue;
    @Column(name = "temperature_above_value")
    private double temperatureAboveValue;

    @Override
    public String getType() {
        return Constants.TEMPERATURE_TYPE;
    }

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

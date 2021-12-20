package com.ivan.weather.station.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "humidity_anomaly_detection_rules")
public class HumidityAnomalyDetectionRule extends AnomalyDetectionRule {

    @Column(name = "humidity_below_value")
    private double humidityBelowValue;
    @Column(name = "humidity_above_value")
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
    public double getBelowValue() {
        return getHumidityBelowValue();
    }

    @Override
    public double getAboveValue() {
        return getHumidityAboveValue();
    }
}

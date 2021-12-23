package com.ivan.weather.station.core.domain.model;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.TemperatureAnomalyDetectionRule;

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

    @Override
    public <T extends AnomalyDetectionRule> T toEntityModel() {
        TemperatureAnomalyDetectionRule temperatureAnomalyDetectionRule = super.toEntityModel();
        temperatureAnomalyDetectionRule.setTemperatureAboveValue(getTemperatureAboveValue());
        temperatureAnomalyDetectionRule.setTemperatureBelowValue(getTemperatureBelowValue());
        return (T) temperatureAnomalyDetectionRule;
    }

    @Override
    protected <T extends AnomalyDetectionRule> T getEntity() {
        return (T) new TemperatureAnomalyDetectionRule();
    }
}

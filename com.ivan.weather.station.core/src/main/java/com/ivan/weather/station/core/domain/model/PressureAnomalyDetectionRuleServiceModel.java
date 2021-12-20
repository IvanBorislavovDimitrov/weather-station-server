package com.ivan.weather.station.core.domain.model;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;

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

    @Override
    public AnomalyDetectionRuleResponseModel toResponseModel() {
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = super.toResponseModel();
        anomalyDetectionRuleResponseModel.setValueBelow(getPressureBelowValue());
        anomalyDetectionRuleResponseModel.setValueAbove(getPressureAboveValue());
        anomalyDetectionRuleResponseModel.setType(AnomalyDetectionRuleType.PRESSURE.getValue());
        return anomalyDetectionRuleResponseModel;
    }

}

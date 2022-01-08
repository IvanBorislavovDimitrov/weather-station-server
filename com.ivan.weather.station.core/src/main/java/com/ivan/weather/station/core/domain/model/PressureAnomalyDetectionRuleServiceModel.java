package com.ivan.weather.station.core.domain.model;

import java.util.List;
import java.util.stream.Collectors;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.core.domain.binding.type.AnomalyDetectionRuleType;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.PressureAnomalyDetectionRule;

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

    @Override
    public <T extends AnomalyDetectionRule> T toEntityModel() {
        PressureAnomalyDetectionRule pressureAnomalyDetectionRule = super.toEntityModel();
        pressureAnomalyDetectionRule.setPressureAboveValue(getPressureAboveValue());
        pressureAnomalyDetectionRule.setPressureBelowValue(getPressureBelowValue());
        return (T) pressureAnomalyDetectionRule;
    }

    @Override
    protected <T extends AnomalyDetectionRule> T getEntity() {
        return (T) new PressureAnomalyDetectionRule();
    }

    @Override
    public boolean isOutOfUpperConstraint(MeasurementServiceModel measurement) {
        return isRuleAboveActivated() && measurement.getPressure() > getPressureAboveValue();
    }

    @Override
    public boolean isOutOfDownConstraint(MeasurementServiceModel measurement) {
        return isRuleBelowActivated() && measurement.getPressure() < getPressureBelowValue();
    }

    @Override
    public List<PowerPlugServiceModel> getPlugsForAnomaly(List<PowerPlugServiceModel> powerPlugs) {
        return powerPlugs.stream()
                         .filter(powerPlug -> AnomalyDetectionRuleType.PRESSURE.getValue()
                                                                               .equalsIgnoreCase(powerPlug.getType()))
                         .collect(Collectors.toList());
    }
}

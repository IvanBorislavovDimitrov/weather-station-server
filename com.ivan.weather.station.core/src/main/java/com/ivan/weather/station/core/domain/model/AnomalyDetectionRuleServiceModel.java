package com.ivan.weather.station.core.domain.model;

import java.util.List;

import com.ivan.weather.station.core.domain.binding.response.AnomalyDetectionRuleResponseModel;
import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;

public abstract class AnomalyDetectionRuleServiceModel extends IdServiceModel {

    private boolean ruleBelowActivated;
    private boolean ruleAboveActivated;
    private RaspberryServiceModel raspberry;

    public AnomalyDetectionRuleResponseModel toResponseModel() {
        AnomalyDetectionRuleResponseModel anomalyDetectionRuleResponseModel = new AnomalyDetectionRuleResponseModel();
        anomalyDetectionRuleResponseModel.setRuleAboveActivated(isRuleAboveActivated());
        anomalyDetectionRuleResponseModel.setRuleBelowActivated(isRuleBelowActivated());
        anomalyDetectionRuleResponseModel.setRaspberryId(getRaspberry().getId());
        return anomalyDetectionRuleResponseModel;
    }

    public <T extends AnomalyDetectionRule> T toEntityModel() {
        T anomalyDetectionRule = getEntity();
        anomalyDetectionRule.setRuleAboveActivated(isRuleAboveActivated());
        anomalyDetectionRule.setRuleBelowActivated(isRuleBelowActivated());
        return anomalyDetectionRule;
    }

    public abstract boolean isOutOfUpperConstraint(MeasurementServiceModel measurement);

    public abstract boolean isOutOfDownConstraint(MeasurementServiceModel measurement);

    public abstract List<PowerPlugServiceModel> getPlugsForAnomaly(List<PowerPlugServiceModel> powerPlugs);

    protected abstract <T extends AnomalyDetectionRule> T getEntity();

    public boolean isRuleBelowActivated() {
        return ruleBelowActivated;
    }

    public void setRuleBelowActivated(boolean ruleBelowActivated) {
        this.ruleBelowActivated = ruleBelowActivated;
    }

    public boolean isRuleAboveActivated() {
        return ruleAboveActivated;
    }

    public void setRuleAboveActivated(boolean ruleAboveActivated) {
        this.ruleAboveActivated = ruleAboveActivated;
    }

    public RaspberryServiceModel getRaspberry() {
        return raspberry;
    }

    public void setRaspberry(RaspberryServiceModel raspberry) {
        this.raspberry = raspberry;
    }
}

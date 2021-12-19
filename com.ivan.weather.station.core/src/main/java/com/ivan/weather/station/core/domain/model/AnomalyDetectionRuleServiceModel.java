package com.ivan.weather.station.core.domain.model;

public abstract class AnomalyDetectionRuleServiceModel extends IdServiceModel {

    private boolean ruleBelowActivated;
    private boolean ruleAboveActivated;

    private RaspberryServiceModel raspberry;

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

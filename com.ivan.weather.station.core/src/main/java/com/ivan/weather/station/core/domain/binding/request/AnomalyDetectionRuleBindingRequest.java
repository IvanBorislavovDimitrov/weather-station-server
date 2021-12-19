package com.ivan.weather.station.core.domain.binding.request;

public class AnomalyDetectionRuleBindingRequest {

    private boolean ruleBelowActivated;
    private boolean ruleAboveActivated;
    private double valueBelow;
    private double valueAbove;
    private String raspberryId;
    private String type;

    public double getValueBelow() {
        return valueBelow;
    }

    public void setValueBelow(double valueBelow) {
        this.valueBelow = valueBelow;
    }

    public double getValueAbove() {
        return valueAbove;
    }

    public void setValueAbove(double valueAbove) {
        this.valueAbove = valueAbove;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getRaspberryId() {
        return raspberryId;
    }

    public void setRaspberryId(String raspberryId) {
        this.raspberryId = raspberryId;
    }
}

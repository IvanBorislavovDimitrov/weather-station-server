package com.ivan.weather.station.persistence.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "anomaly_detection_rules")
public abstract class AnomalyDetectionRule extends IdEntity {

    @Column(name = "rule_below_activated")
    private boolean ruleBelowActivated;
    @Column(name = "rule_above_activated")
    private boolean ruleAboveActivated;
    @ManyToOne
    @JoinColumn(name = "raspberry_id", referencedColumnName = "id")
    private Raspberry raspberry;

    public abstract String getType();

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

    public Raspberry getRaspberry() {
        return raspberry;
    }

    public void setRaspberry(Raspberry raspberry) {
        this.raspberry = raspberry;
    }
}

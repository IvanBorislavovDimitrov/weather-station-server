package com.ivan.weather.station.persistence.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "raspberries")
public class Raspberry extends IdEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String route;
    @Lob
    private String description;
    @Column(name = "is_started")
    private boolean isStarted;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "raspberry", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Measurement.class)
    private List<Measurement> measurements = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "raspberry", targetEntity = AnomalyDetectionRule.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<AnomalyDetectionRule> anomalyDetectionRules = Collections.emptyList();
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "raspberry", targetEntity = PowerPlug.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PowerPlug> powerPlugs = Collections.emptyList();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public List<AnomalyDetectionRule> getAnomalyDetectionRules() {
        return anomalyDetectionRules;
    }

    public void setAnomalyDetectionRules(List<AnomalyDetectionRule> anomalyDetectionRules) {
        this.anomalyDetectionRules = anomalyDetectionRules;
    }

    public List<PowerPlug> getPowerPlugs() {
        return powerPlugs;
    }

    public void setPowerPlugs(List<PowerPlug> powerPlugs) {
        this.powerPlugs = powerPlugs;
    }
}

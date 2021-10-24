package com.ivan.weather.station.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "raspberries")
public class Raspberry extends IdEntity {

    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String route;
    @Lob
    private String description;
    @Column(name = "is_started")
    private boolean isStarted;
    @OneToMany(mappedBy = "raspberry", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Measurement.class)
    private List<Measurement> measurements = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

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
}
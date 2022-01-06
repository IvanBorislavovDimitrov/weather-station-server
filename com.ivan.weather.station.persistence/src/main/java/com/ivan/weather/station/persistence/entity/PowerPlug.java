package com.ivan.weather.station.persistence.entity;

import javax.persistence.*;

@Entity
@Table(name = "power_plugs")
public class PowerPlug extends IdEntity {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "route", nullable = false, unique = true)
    private String route;
    @Column(name = "is_started", nullable = false)
    private boolean isStarted;
    @Lob
    private String description;
    @ManyToOne
    @JoinColumn(name = "raspberry_id", referencedColumnName = "id")
    private Raspberry raspberry;

    public Raspberry getRaspberry() {
        return raspberry;
    }

    public void setRaspberry(Raspberry raspberry) {
        this.raspberry = raspberry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

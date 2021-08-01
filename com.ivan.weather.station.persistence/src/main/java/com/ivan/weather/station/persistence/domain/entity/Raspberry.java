package com.ivan.weather.station.persistence.domain.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "raspberries")
public class Raspberry extends IdEntity {

    @Column(nullable = false, unique = true)
    private String route;
    @Lob
    private String description;
    @OneToMany(mappedBy = "raspberry", fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Measurement.class)
    private List<Measurement> measurements = new ArrayList<>();

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
}

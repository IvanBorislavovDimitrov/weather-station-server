package com.ivan.weather.station.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "measurements")
public class Measurement extends IdEntity {

    @Column(nullable = false)
    private double temperature;
    @Column(nullable = false)
    private double humidity;
    @Column(name = "added_on", nullable = false)
    private LocalDateTime addedOn;
    @ManyToOne
    @JoinColumn(name = "raspberry_id", referencedColumnName = "id")
    private Raspberry raspberry;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }


    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Raspberry getRaspberry() {
        return raspberry;
    }

    public void setRaspberry(Raspberry raspberry) {
        this.raspberry = raspberry;
    }
}

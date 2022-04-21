package com.ivan.weather.station.core.domain.model;

import java.time.LocalDateTime;

public class SubscribedEmailServiceModel extends IdServiceModel {

    private String email;

    private LocalDateTime addedOn;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }
}

package com.ivan.weather.station.core.domain.binding.response;

import java.time.LocalDateTime;

public class SubscribedEmailResponseModel {

    private String id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

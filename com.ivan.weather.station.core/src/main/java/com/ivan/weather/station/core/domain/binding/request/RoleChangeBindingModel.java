package com.ivan.weather.station.core.domain.binding.request;

import javax.validation.constraints.NotNull;

public class RoleChangeBindingModel {

    @NotNull
    private String username;
    @NotNull
    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}

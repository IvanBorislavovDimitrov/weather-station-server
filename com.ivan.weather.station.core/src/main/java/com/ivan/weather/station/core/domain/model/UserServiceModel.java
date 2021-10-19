package com.ivan.weather.station.core.domain.model;

import java.util.Collections;
import java.util.List;

public class UserServiceModel extends IdServiceModel {

    private String username;
    private String password;
    private String email;
    private List<RoleServiceModel> roles = Collections.emptyList();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<RoleServiceModel> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleServiceModel> roles) {
        this.roles = roles;
    }
}

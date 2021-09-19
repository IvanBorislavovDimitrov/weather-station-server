package com.ivan.weather.station.persistence.domain.model;

public class RoleServiceModel extends IdServiceModel {

    private String roleType;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}

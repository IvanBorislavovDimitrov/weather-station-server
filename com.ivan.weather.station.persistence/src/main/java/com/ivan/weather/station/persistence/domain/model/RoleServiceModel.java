package com.ivan.weather.station.persistence.domain.model;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceModel extends IdServiceModel {

    private String roleType;

    private List<UserServiceModel> users = new ArrayList<>();

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public List<UserServiceModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserServiceModel> users) {
        this.users = users;
    }
}

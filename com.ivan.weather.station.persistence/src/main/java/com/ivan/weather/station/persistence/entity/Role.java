package com.ivan.weather.station.persistence.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "roles")
public class Role extends IdEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany
    @JoinTable(name = "roles_users", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = Collections.emptyList();

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public enum RoleType {
        ADMIN, MODERATOR, USER;

        @Override
        public String toString() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

}
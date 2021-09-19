package com.ivan.weather.station.persistence.domain.entity;

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

    @ManyToMany
    @JoinTable(name = "roles_users", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = Collections.emptyList();

    private enum RoleType {
        ADMIN, MODERATOR, USER;

        @Override
        public String toString() {
            return name().toLowerCase(Locale.ROOT);
        }
    }

}
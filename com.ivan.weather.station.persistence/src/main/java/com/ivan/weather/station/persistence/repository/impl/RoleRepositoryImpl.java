package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.entity.Role;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public class RoleRepositoryImpl extends BaseRepositoryImpl<Role> implements RoleRepository {

    @Autowired
    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @PostConstruct
    public void initRoles() {
        if (count() == 0) {
            for (Role.RoleType roleType : Role.RoleType.values()) {
                Role role = new Role();
                role.setRoleType(roleType);
                save(role);
            }
        }
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}

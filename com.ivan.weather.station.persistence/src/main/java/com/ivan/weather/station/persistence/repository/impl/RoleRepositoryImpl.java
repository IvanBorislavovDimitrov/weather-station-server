package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.domain.entity.Role;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl extends BaseRepositoryImpl<Role> implements RoleRepository {

    @Autowired
    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}

package com.ivan.weather.station.persistence.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.PowerPlug;
import com.ivan.weather.station.persistence.repository.api.PowerPlugRepository;

@Repository
public class PowerPlugRepositoryImpl extends BaseRepositoryImpl<PowerPlug> implements PowerPlugRepository {

    @Autowired
    public PowerPlugRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<PowerPlug> getEntityClass() {
        return PowerPlug.class;
    }
}

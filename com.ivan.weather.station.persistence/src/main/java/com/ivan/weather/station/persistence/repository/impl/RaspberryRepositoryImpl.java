package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.domain.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RaspberryRepositoryImpl extends BaseRepositoryImpl<Raspberry> implements RaspberryRepository {

    public RaspberryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Raspberry> getEntityClass() {
        return Raspberry.class;
    }

}

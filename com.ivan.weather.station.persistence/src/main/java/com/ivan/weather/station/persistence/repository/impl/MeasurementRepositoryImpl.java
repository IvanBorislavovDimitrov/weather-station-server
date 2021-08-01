package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.domain.entity.Measurement;
import com.ivan.weather.station.persistence.repository.api.MeasurementRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MeasurementRepositoryImpl extends BaseRepositoryImpl<Measurement> implements MeasurementRepository {

    public MeasurementRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Measurement> getEntityClass() {
        return Measurement.class;
    }
}

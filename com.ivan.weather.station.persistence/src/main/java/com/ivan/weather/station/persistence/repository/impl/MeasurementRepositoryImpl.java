package com.ivan.weather.station.persistence.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.criteria.*;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.Measurement;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.MeasurementRepository;

@Repository
public class MeasurementRepositoryImpl extends BaseRepositoryImpl<Measurement> implements MeasurementRepository {

    public MeasurementRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Measurement> findMeasurementsBetween(LocalDateTime startPeriod, LocalDateTime endPeriod, String raspberryId) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Measurement> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<Measurement> root = criteriaQuery.from(getEntityClass());
            Join<Measurement, Raspberry> join = root.join("raspberry");
            Predicate equalById = criteriaBuilder.equal(join.get("id"), raspberryId);
            Predicate greaterThan = criteriaBuilder.greaterThan(root.get("addedOn"), startPeriod);
            Predicate lessThan = criteriaBuilder.lessThan(root.get("addedOn"), endPeriod);
            criteriaQuery.where(criteriaBuilder.and(equalById, greaterThan, lessThan));
            return session.createQuery(criteriaQuery)
                          .getResultList();
        });
    }

    @Override
    protected Class<Measurement> getEntityClass() {
        return Measurement.class;
    }
}

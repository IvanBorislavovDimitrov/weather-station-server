package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.entity.Measurement;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.MeasurementRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

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
            criteriaQuery.where(criteriaBuilder.equal(join.get("id"), raspberryId));
            criteriaQuery.where(criteriaBuilder.greaterThan(root.get("addedOn"), startPeriod));
            criteriaQuery.where(criteriaBuilder.lessThan(root.get("addedOn"), endPeriod));
            return session.createQuery(criteriaQuery).getResultList();
        });
    }

    @Override
    protected Class<Measurement> getEntityClass() {
        return Measurement.class;
    }
}

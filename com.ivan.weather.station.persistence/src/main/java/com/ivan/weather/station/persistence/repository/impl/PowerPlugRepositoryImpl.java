package com.ivan.weather.station.persistence.repository.impl;

import java.util.List;

import javax.persistence.criteria.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.PowerPlug;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.PowerPlugRepository;

@Repository
public class PowerPlugRepositoryImpl extends BaseRepositoryImpl<PowerPlug> implements PowerPlugRepository {

    @Autowired
    public PowerPlugRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<PowerPlug> findAllByRaspberryId(String raspberryId) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<PowerPlug> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<PowerPlug> root = criteriaQuery.from(getEntityClass());
            Join<PowerPlug, Raspberry> join = root.join("raspberry");
            Predicate equalById = criteriaBuilder.equal(join.get("id"), raspberryId);
            criteriaQuery.where(criteriaBuilder.and(equalById));
            return session.createQuery(criteriaQuery)
                          .getResultList();
        });
    }

    @Override
    protected Class<PowerPlug> getEntityClass() {
        return PowerPlug.class;
    }
}

package com.ivan.weather.station.persistence.repository.impl;

import java.util.List;

import javax.persistence.criteria.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.AnomalyDetectionRuleRepository;

@Repository
public class AnomalyDetectionRepositoryImpl extends BaseRepositoryImpl<AnomalyDetectionRule> implements AnomalyDetectionRuleRepository {

    @Autowired
    public AnomalyDetectionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<AnomalyDetectionRule> findByRaspberryId(String raspberryId) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<AnomalyDetectionRule> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<AnomalyDetectionRule> root = criteriaQuery.from(getEntityClass());
            Join<AnomalyDetectionRule, Raspberry> join = root.join("raspberry");
            Predicate equalById = criteriaBuilder.equal(join.get("id"), raspberryId);
            criteriaQuery.where(criteriaBuilder.and(equalById));
            return session.createQuery(criteriaQuery)
                          .getResultList();
        });
    }

    @Override
    protected Class<AnomalyDetectionRule> getEntityClass() {
        return AnomalyDetectionRule.class;
    }
}

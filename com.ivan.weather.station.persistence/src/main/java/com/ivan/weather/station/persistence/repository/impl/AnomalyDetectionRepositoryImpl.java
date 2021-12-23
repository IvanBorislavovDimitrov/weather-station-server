package com.ivan.weather.station.persistence.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.AnomalyDetectionRule;
import com.ivan.weather.station.persistence.repository.api.AnomalyDetectionRuleRepository;

@Repository
public class AnomalyDetectionRepositoryImpl extends BaseRepositoryImpl<AnomalyDetectionRule> implements AnomalyDetectionRuleRepository {

    @Autowired
    public AnomalyDetectionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<AnomalyDetectionRule> getEntityClass() {
        return AnomalyDetectionRule.class;
    }
}

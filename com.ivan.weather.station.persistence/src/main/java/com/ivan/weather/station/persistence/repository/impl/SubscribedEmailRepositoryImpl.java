package com.ivan.weather.station.persistence.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.SubscribedEmail;
import com.ivan.weather.station.persistence.repository.api.SubscribedEmailRepository;

@Repository
public class SubscribedEmailRepositoryImpl extends BaseRepositoryImpl<SubscribedEmail> implements SubscribedEmailRepository {

    @Autowired
    public SubscribedEmailRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<SubscribedEmail> getEntityClass() {
        return SubscribedEmail.class;
    }
}

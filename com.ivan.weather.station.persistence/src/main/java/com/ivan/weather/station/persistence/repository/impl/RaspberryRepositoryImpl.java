package com.ivan.weather.station.persistence.repository.impl;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ivan.weather.station.persistence.entity.Raspberry;
import com.ivan.weather.station.persistence.repository.api.RaspberryRepository;

@Repository
public class RaspberryRepositoryImpl extends BaseRepositoryImpl<Raspberry> implements RaspberryRepository {

    public RaspberryRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    protected Class<Raspberry> getEntityClass() {
        return Raspberry.class;
    }

    @Override
    public Optional<Raspberry> findByRoute(String route) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Raspberry> query = criteriaBuilder.createQuery(Raspberry.class);
            Root<Raspberry> root = query.from(Raspberry.class);
            query.select(root)
                 .where(criteriaBuilder.equal(root.get("route"), route));
            return getOrEmpty(session, query);
        });
    }
}

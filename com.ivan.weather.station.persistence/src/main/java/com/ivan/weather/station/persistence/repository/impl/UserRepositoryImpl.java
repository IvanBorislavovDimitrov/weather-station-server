package com.ivan.weather.station.persistence.repository.impl;

import com.ivan.weather.station.persistence.domain.entity.User;
import com.ivan.weather.station.persistence.repository.api.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<User> root = criteriaQuery.from(getEntityClass());
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
            return getOrEmpty(session, criteriaQuery);
        });
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

}

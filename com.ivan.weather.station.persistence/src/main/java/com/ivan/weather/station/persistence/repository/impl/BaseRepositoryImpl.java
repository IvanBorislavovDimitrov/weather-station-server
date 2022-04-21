package com.ivan.weather.station.persistence.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ivan.weather.station.persistence.entity.Measurement;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ivan.weather.station.persistence.entity.IdEntity;
import com.ivan.weather.station.persistence.repository.api.BaseRepository;

public abstract class BaseRepositoryImpl<E extends IdEntity> implements BaseRepository<E> {

    protected final SessionFactory sessionFactory;

    protected BaseRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(E obj) {
        executeInTransaction(session -> {
            session.save(obj);
            return null;
        });
    }

    @Override
    public void update(E obj) {
        executeInTransaction(session -> {
            session.update(obj);
            return null;
        });
    }

    @Override
    public Optional<E> findById(String id) {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<E> root = criteriaQuery.from(getEntityClass());
            criteriaQuery.select(root)
                         .where(criteriaBuilder.equal(root.get("id"), id));
            return getOrEmpty(session, criteriaQuery);
        });
    }

    protected <T> Optional<T> getOrEmpty(Session session, CriteriaQuery<T> criteriaQuery) {
        try {
            return getSingleResult(session, criteriaQuery);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    private <T> Optional<T> getSingleResult(Session session, CriteriaQuery<T> criteriaQuery) {
        return Optional.of(session.createQuery(criteriaQuery)
                                  .getSingleResult());
    }

    @Override
    public List<E> findAll() {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
            Root<E> root = criteriaQuery.from(getEntityClass());
            criteriaQuery.select(root);
            Query<E> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        });
    }

    @Override
    public long count() {
        return executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> cr = criteriaBuilder.createQuery(Long.class);
            Root<E> root = cr.from(getEntityClass());
            cr.select(criteriaBuilder.count(root));
            Query<Long> query = session.createQuery(cr);
            return query.getSingleResult();
        });
    }

    @Override
    public void delete(String id) {
        executeInTransaction(session -> {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaDelete<E> criteriaDelete = criteriaBuilder.createCriteriaDelete(getEntityClass());
            Root<E> root = criteriaDelete.from(getEntityClass());
            criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
            session.createQuery(criteriaDelete).executeUpdate();
            return null;
        });
    }

    protected <T> T executeInTransaction(Function<Session, T> callback) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            T entity = callback.apply(session);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            session.close();
        }
    }

    protected abstract Class<E> getEntityClass();

}

package com.ivan.weather.station.persistence.repository.api;

import java.util.List;
import java.util.Optional;

import com.ivan.weather.station.persistence.entity.IdEntity;

public interface BaseRepository<E extends IdEntity> {

    void save(E obj);

    void update(E obj);

    Optional<E> findById(String id);

    List<E> findAll();

    long count();

    void delete(String id);

}

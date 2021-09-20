package com.ivan.weather.station.persistence.repository.api;

import com.ivan.weather.station.persistence.domain.entity.IdEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<E extends IdEntity> {

    void save(E obj);

    void update(E obj);

    Optional<E> findById(String id);

    List<E> findAll();

    long count();

}

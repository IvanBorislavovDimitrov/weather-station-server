package com.ivan.weather.station.persistence.service.api;

import com.ivan.weather.station.persistence.domain.model.IdServiceModel;

import java.util.List;

public interface BaseService<M extends IdServiceModel> {

    void save(M obj);

    M findById(String id);

    List<M> findAll();

    long count();
}

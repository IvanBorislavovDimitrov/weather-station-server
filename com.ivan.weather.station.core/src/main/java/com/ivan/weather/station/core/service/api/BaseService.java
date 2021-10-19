package com.ivan.weather.station.core.service.api;

import com.ivan.weather.station.core.domain.model.IdServiceModel;

import java.util.List;

public interface BaseService<M extends IdServiceModel> {

    void save(M obj);

    M findById(String id);

    List<M> findAll();

    long count();
}

package com.ivan.weather.station.core.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.ivan.weather.station.core.domain.model.IdServiceModel;
import com.ivan.weather.station.core.service.api.BaseService;
import com.ivan.weather.station.persistence.entity.IdEntity;
import com.ivan.weather.station.persistence.repository.api.BaseRepository;

public abstract class BaseServiceImpl<E extends IdEntity, M extends IdServiceModel> implements BaseService<M> {

    protected final ModelMapper modelMapper;
    private final BaseRepository<E> baseRepository;

    protected BaseServiceImpl(BaseRepository<E> baseRepository, ModelMapper modelMapper) {
        this.baseRepository = baseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(M obj) {
        E entity = modelMapper.map(obj, getEntityClass());
        baseRepository.save(entity);
    }

    @Override
    public M findById(String id) {
        Optional<E> entity = baseRepository.findById(id);
        if (entity.isEmpty()) {
            throw new IllegalArgumentException("Not found");
        }
        return modelMapper.map(entity, getModelClass());
    }

    @Override
    public List<M> findAll() {
        List<E> entities = baseRepository.findAll();
        return entities.stream()
                       .map(entity -> modelMapper.map(entity, getModelClass()))
                       .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return baseRepository.count();
    }

    protected abstract Class<E> getEntityClass();

    protected abstract Class<M> getModelClass();

}

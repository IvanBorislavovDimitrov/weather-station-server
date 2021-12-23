package com.ivan.weather.station.core.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.model.RoleServiceModel;
import com.ivan.weather.station.core.service.api.RoleService;
import com.ivan.weather.station.persistence.entity.Role;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleServiceModel> implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        super(roleRepository, modelMapper);
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    protected Class<RoleServiceModel> getModelClass() {
        return RoleServiceModel.class;
    }
}

package com.ivan.weather.station.persistence.service.impl;

import com.ivan.weather.station.persistence.domain.entity.Role;
import com.ivan.weather.station.persistence.domain.model.RoleServiceModel;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;
import com.ivan.weather.station.persistence.service.api.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

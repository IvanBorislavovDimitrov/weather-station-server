package com.ivan.weather.station.core.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.model.SubscribedEmailServiceModel;
import com.ivan.weather.station.core.service.api.SubscribedEmailService;
import com.ivan.weather.station.persistence.entity.SubscribedEmail;
import com.ivan.weather.station.persistence.repository.api.BaseRepository;

@Service
public class SubscribedEmailServiceImpl extends BaseServiceImpl<SubscribedEmail, SubscribedEmailServiceModel>
    implements SubscribedEmailService {

    @Autowired
    public SubscribedEmailServiceImpl(BaseRepository<SubscribedEmail> baseRepository, ModelMapper modelMapper) {
        super(baseRepository, modelMapper);
    }

    @Override
    protected Class<SubscribedEmail> getEntityClass() {
        return SubscribedEmail.class;
    }

    @Override
    protected Class<SubscribedEmailServiceModel> getModelClass() {
        return SubscribedEmailServiceModel.class;
    }
}

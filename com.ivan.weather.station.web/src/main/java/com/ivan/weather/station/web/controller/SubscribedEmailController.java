package com.ivan.weather.station.web.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.weather.station.core.domain.binding.request.SubscribedEmailBindingModel;
import com.ivan.weather.station.core.domain.binding.response.SubscribedEmailResponseModel;
import com.ivan.weather.station.core.domain.model.SubscribedEmailServiceModel;
import com.ivan.weather.station.core.service.api.SubscribedEmailService;

@RestController
@RequestMapping(value = "/subscribe")
public class SubscribedEmailController {

    private final ModelMapper modelMapper;
    private final SubscribedEmailService subscribedEmailService;

    @Autowired
    public SubscribedEmailController(ModelMapper modelMapper, SubscribedEmailService subscribedEmailService) {
        this.modelMapper = modelMapper;
        this.subscribedEmailService = subscribedEmailService;
    }

    @PostMapping
    public ResponseEntity<SubscribedEmailResponseModel> add(@RequestBody @Valid SubscribedEmailBindingModel subscribedEmailBindingModel) {
        SubscribedEmailServiceModel subscribedEmailServiceModel = modelMapper.map(subscribedEmailBindingModel,
                                                                                  SubscribedEmailServiceModel.class);
        subscribedEmailService.save(subscribedEmailServiceModel);
        return ResponseEntity.ok(modelMapper.map(subscribedEmailServiceModel, SubscribedEmailResponseModel.class));
    }
}

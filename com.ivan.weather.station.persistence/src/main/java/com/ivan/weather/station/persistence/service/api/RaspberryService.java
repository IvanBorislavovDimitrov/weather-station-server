package com.ivan.weather.station.persistence.service.api;

import com.ivan.weather.station.persistence.domain.model.RaspberryServiceModel;

public interface RaspberryService extends BaseService<RaspberryServiceModel> {

    void start(String id);

    void stop(String id);
}

package com.ivan.weather.station.core.service.api;

import com.ivan.weather.station.core.domain.model.RaspberryServiceModel;

public interface RaspberryService extends BaseService<RaspberryServiceModel> {

    void start(String id);

    void stop(String id);
}

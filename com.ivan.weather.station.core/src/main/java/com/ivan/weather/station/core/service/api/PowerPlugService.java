package com.ivan.weather.station.core.service.api;

import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;

import java.util.List;

public interface PowerPlugService extends BaseService<PowerPlugServiceModel> {

    List<PowerPlugServiceModel> findAllByRaspberryId(String raspberryId);
}

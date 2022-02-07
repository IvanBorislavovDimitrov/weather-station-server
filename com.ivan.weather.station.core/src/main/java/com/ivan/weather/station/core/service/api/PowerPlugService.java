package com.ivan.weather.station.core.service.api;

import java.util.List;

import com.ivan.weather.station.core.domain.model.PowerPlugServiceModel;

public interface PowerPlugService extends BaseService<PowerPlugServiceModel> {

    List<PowerPlugServiceModel> findAllByRaspberryId(String raspberryId);
}

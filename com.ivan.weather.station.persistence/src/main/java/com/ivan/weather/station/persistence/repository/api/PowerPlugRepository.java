package com.ivan.weather.station.persistence.repository.api;

import java.util.List;

import com.ivan.weather.station.persistence.entity.PowerPlug;

public interface PowerPlugRepository extends BaseRepository<PowerPlug> {

    List<PowerPlug> findAllByRaspberryId(String raspberryId);
}

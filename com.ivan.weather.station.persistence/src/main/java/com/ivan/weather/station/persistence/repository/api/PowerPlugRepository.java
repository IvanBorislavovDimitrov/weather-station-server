package com.ivan.weather.station.persistence.repository.api;

import com.ivan.weather.station.persistence.entity.PowerPlug;

import java.util.List;

public interface PowerPlugRepository extends BaseRepository<PowerPlug> {

    List<PowerPlug> findAllByRaspberryId(String raspberryId);
}

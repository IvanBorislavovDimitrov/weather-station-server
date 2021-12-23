package com.ivan.weather.station.persistence.repository.api;

import java.util.Optional;

import com.ivan.weather.station.persistence.entity.Raspberry;

public interface RaspberryRepository extends BaseRepository<Raspberry> {

    Optional<Raspberry> findByRoute(String route);
}

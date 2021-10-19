package com.ivan.weather.station.persistence.repository.api;

import com.ivan.weather.station.persistence.entity.Raspberry;

import java.util.Optional;

public interface RaspberryRepository extends BaseRepository<Raspberry> {

    Optional<Raspberry> findByRoute(String route);
}

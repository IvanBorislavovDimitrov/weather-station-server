package com.ivan.weather.station.persistence.repository.api;

import com.ivan.weather.station.persistence.entity.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

}

package com.ivan.weather.station.persistence.repository.api;

import java.util.Optional;

import com.ivan.weather.station.persistence.entity.User;

public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByUsername(String username);

}

package com.ivan.weather.station.persistence.service.api;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ivan.weather.station.persistence.domain.model.UserServiceModel;

public interface UserService extends BaseService<UserServiceModel>, UserDetailsService {

    void activate(String username);

}

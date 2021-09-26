package com.ivan.weather.station.persistence.service.api;

import com.ivan.weather.station.persistence.domain.binding.response.RaspberryResponseBindingModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ivan.weather.station.persistence.domain.model.UserServiceModel;

import java.util.List;

public interface UserService extends BaseService<UserServiceModel>, UserDetailsService {

    void activate(String username);

    UserServiceModel findUserByUsername(String username);

    List<RaspberryResponseBindingModel> findUserRaspberries(String username);
}

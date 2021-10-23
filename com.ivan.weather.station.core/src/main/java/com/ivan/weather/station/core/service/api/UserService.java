package com.ivan.weather.station.core.service.api;

import com.ivan.weather.station.core.domain.binding.response.RaspberryResponseModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.ivan.weather.station.core.domain.model.UserServiceModel;

import java.util.List;

public interface UserService extends BaseService<UserServiceModel>, UserDetailsService {

    void activate(String username);

    UserServiceModel findUserByUsername(String username);

    List<RaspberryResponseModel> findUserRaspberries(String username);
}

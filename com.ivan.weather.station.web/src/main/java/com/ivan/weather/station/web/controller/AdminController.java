package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.core.domain.binding.request.RoleChangeBindingModel;
import com.ivan.weather.station.core.domain.binding.response.RoleChangeResponseModel;
import com.ivan.weather.station.core.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public AdminController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/change-role")
    public ResponseEntity<RoleChangeResponseModel> changeRole(@RequestBody @Valid RoleChangeBindingModel roleChangeBindingModel) {
        userService.changeUserRole(roleChangeBindingModel.getUsername(), roleChangeBindingModel.getRoles());
        return ResponseEntity.ok(modelMapper.map(roleChangeBindingModel, RoleChangeResponseModel.class));
    }
}

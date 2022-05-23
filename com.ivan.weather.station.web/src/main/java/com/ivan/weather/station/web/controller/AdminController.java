package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.core.domain.binding.request.RoleChangeBindingModel;
import com.ivan.weather.station.core.domain.binding.response.RoleChangeResponseModel;
import com.ivan.weather.station.core.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR ROLE_ADMIN')")
    public ResponseEntity<RoleChangeResponseModel> changeRole(@RequestBody @Valid RoleChangeBindingModel roleChangeBindingModel) {
        userService.changeUserRole(roleChangeBindingModel.getUsername(), roleChangeBindingModel.getRoles());
        return ResponseEntity.ok(modelMapper.map(roleChangeBindingModel, RoleChangeResponseModel.class));
    }

    @PostMapping(value = "/delete-user/{username}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteByUsername(username);
        return ResponseEntity.ok()
                             .build();
    }
}

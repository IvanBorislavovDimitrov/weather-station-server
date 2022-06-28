package com.ivan.weather.station.web.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivan.weather.station.core.domain.binding.request.UserRegistrationBindingModel;
import com.ivan.weather.station.core.domain.binding.response.RaspberryResponseModel;
import com.ivan.weather.station.core.domain.binding.response.UserRegistrationResponseModel;
import com.ivan.weather.station.core.domain.model.UserServiceModel;
import com.ivan.weather.station.core.service.api.UserService;
import com.ivan.weather.station.persistence.entity.Role;
import com.ivan.weather.station.persistence.entity.User;
import com.ivan.weather.station.web.authentication.AuthenticationRequest;
import com.ivan.weather.station.web.authentication.JwtTokenResponse;
import com.ivan.weather.station.web.util.JwtUtil;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserRegistrationResponseModel>
           register(@RequestBody @Valid UserRegistrationBindingModel userRegistrationBindingModel) {
        if (!Objects.equals(userRegistrationBindingModel.getConfirmPassword(), userRegistrationBindingModel.getPassword())) {
            return ResponseEntity.badRequest()
                                 .build();
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegistrationBindingModel, UserServiceModel.class);
        userService.save(userServiceModel);
        return ResponseEntity.ok(modelMapper.map(userRegistrationBindingModel, UserRegistrationResponseModel.class));
    }

    @GetMapping("/raspberries")
    public ResponseEntity<List<RaspberryResponseModel>> findUserRaspberries() {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            return ResponseEntity.ok(userService.findUserRaspberries(currentUserName));
        }
        return ResponseEntity.status(403)
                             .build();
    }

    @PostMapping(value = "/activate/{username}")
    public ResponseEntity<Void> activate(@PathVariable String username) {
        userService.activate(username);
        return ResponseEntity.ok()
                             .build();
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtTokenResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                                                                                                                          authenticationRequest.getPassword());
        User userServiceModel = (User) userService.loadUserByUsername(authenticationRequest.getUsername());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String jwt = JwtUtil.generateToken(userServiceModel, userServiceModel.getRoles()
                                                                             .stream()
                                                                             .map(Role::getRoleType)
                                                                             .map(Role.RoleType::toString)
                                                                             .collect(Collectors.toList()));
        return ResponseEntity.ok(new JwtTokenResponse(jwt));
    }

}

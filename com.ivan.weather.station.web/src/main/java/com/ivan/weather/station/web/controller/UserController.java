package com.ivan.weather.station.web.controller;

import com.ivan.weather.station.persistence.domain.binding.request.UserRegistrationRequestBindingModel;
import com.ivan.weather.station.persistence.domain.entity.Role;
import com.ivan.weather.station.persistence.domain.entity.User;
import com.ivan.weather.station.persistence.domain.model.UserServiceModel;
import com.ivan.weather.station.persistence.service.api.UserService;
import com.ivan.weather.station.web.authentication.AuthenticationRequest;
import com.ivan.weather.station.web.authentication.JwtTokenResponse;
import com.ivan.weather.station.web.mail.Email;
import com.ivan.weather.station.web.mail.EmailClient;
import com.ivan.weather.station.web.util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final EmailClient emailClient;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, EmailClient emailClient,
                          AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.emailClient = emailClient;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationRequestBindingModel userRegistrationRequestBindingModel) {
        if (!Objects.equals(userRegistrationRequestBindingModel.getConfirmPassword(), userRegistrationRequestBindingModel.getPassword())) {
            return ResponseEntity.badRequest()
                    .build();
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegistrationRequestBindingModel, UserServiceModel.class);
        userService.save(userServiceModel);
        Email email = new Email.Builder()
                .setContent("Active user by clicking the following linK " + "http://127.0.0.1/user/activate/" + userServiceModel.getUsername())
                .setRecipient(userServiceModel.getEmail())
                .setTitle("Activate profile").build();
        emailClient.sendAsync(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/activate/{username}")
    public ResponseEntity<Void> activate(@PathVariable String username) {
        userService.activate(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtTokenResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        User userServiceModel = (User) userService.loadUserByUsername(authenticationRequest.getUsername());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        String jwt = JwtUtil.generateToken(userServiceModel, userServiceModel.getRoles()
                .stream().map(Role::getRoleType)
                .map(Role.RoleType::toString)
                .collect(Collectors.toList()));
        return ResponseEntity.ok(new JwtTokenResponse(jwt));
    }


}

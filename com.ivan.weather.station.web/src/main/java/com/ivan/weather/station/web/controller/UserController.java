package com.ivan.weather.station.web.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.ivan.weather.station.core.domain.binding.request.UserRegistrationRequestBindingModel;
import com.ivan.weather.station.core.domain.binding.response.RaspberryResponseModel;
import com.ivan.weather.station.core.domain.binding.response.UserRegistrationResponseModel;
import com.ivan.weather.station.core.domain.model.UserServiceModel;
import com.ivan.weather.station.core.service.api.UserService;
import com.ivan.weather.station.persistence.entity.Role;
import com.ivan.weather.station.persistence.entity.User;
import com.ivan.weather.station.web.authentication.AuthenticationRequest;
import com.ivan.weather.station.web.authentication.JwtTokenResponse;
import com.ivan.weather.station.core.mail.Email;
import com.ivan.weather.station.core.mail.EmailClient;
import com.ivan.weather.station.web.util.JwtUtil;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<UserRegistrationResponseModel>
           register(@RequestBody UserRegistrationRequestBindingModel userRegistrationRequestBindingModel) {
        if (!Objects.equals(userRegistrationRequestBindingModel.getConfirmPassword(), userRegistrationRequestBindingModel.getPassword())) {
            return ResponseEntity.badRequest()
                                 .build();
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegistrationRequestBindingModel, UserServiceModel.class);
        userService.save(userServiceModel);
        Email email = new Email.Builder().setContent("Active user by clicking the following linK " + "http://127.0.0.1:8080/user/activate/"
            + userServiceModel.getUsername())
                                         .setRecipient(userServiceModel.getEmail())
                                         .setTitle("Activate profile")
                                         .build();
        emailClient.sendAsync(email);
        return ResponseEntity.ok(modelMapper.map(userRegistrationRequestBindingModel, UserRegistrationResponseModel.class));
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

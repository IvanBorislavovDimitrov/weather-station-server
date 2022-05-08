package com.ivan.weather.station.core.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ivan.weather.station.core.mail.Email;
import com.ivan.weather.station.core.mail.EmailClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivan.weather.station.core.domain.binding.response.RaspberryResponseModel;
import com.ivan.weather.station.core.domain.model.UserServiceModel;
import com.ivan.weather.station.core.service.api.UserService;
import com.ivan.weather.station.persistence.entity.User;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;
import com.ivan.weather.station.persistence.repository.api.UserRepository;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserServiceModel> implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailClient emailClient;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, EmailClient emailClient,
                           RoleRepository roleRepository) {
        super(userRepository, modelMapper);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailClient = emailClient;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(UserServiceModel userServiceModel) {
        validateUsernameIsFree(userServiceModel);
        userServiceModel.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        super.save(userServiceModel);
        Email email = new Email.Builder().setContent("Active user by clicking the following link " + "http://127.0.0.1:3000/user/activate/"
            + userServiceModel.getUsername())
                                         .setRecipient(userServiceModel.getEmail())
                                         .setTitle("Activate profile")
                                         .build();
        emailClient.sendAsync(email);
    }

    private void validateUsernameIsFree(UserServiceModel userServiceModel) {
        Optional<User> user = userRepository.findByUsername(userServiceModel.getUsername());
        if (user.isPresent()) {
            throw new IllegalArgumentException(String.format("Username \"%s\" is taken!", userServiceModel.getUsername()));
        }
    }

    @Override
    public void activate(String username) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException(username));
        user.setEnabled(true);
        userRepository.update(user);
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    protected Class<UserServiceModel> getModelClass() {
        return UserServiceModel.class;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                             .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserServiceModel findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                             .map(user -> modelMapper.map(user, UserServiceModel.class))
                             .orElseThrow(() -> new UsernameNotFoundException(username));

    }

    @Override
    public List<RaspberryResponseModel> findUserRaspberries(String username) {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException(username));
        return user.getRaspberries()
                   .stream()
                   .map(raspberry -> modelMapper.map(raspberry, RaspberryResponseModel.class))
                   .collect(Collectors.toList());
    }
}

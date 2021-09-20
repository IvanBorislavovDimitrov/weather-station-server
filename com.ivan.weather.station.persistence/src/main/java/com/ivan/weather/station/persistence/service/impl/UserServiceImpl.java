package com.ivan.weather.station.persistence.service.impl;

import com.ivan.weather.station.persistence.domain.entity.Role;
import com.ivan.weather.station.persistence.domain.entity.User;
import com.ivan.weather.station.persistence.domain.model.RoleServiceModel;
import com.ivan.weather.station.persistence.domain.model.UserServiceModel;
import com.ivan.weather.station.persistence.repository.api.RoleRepository;
import com.ivan.weather.station.persistence.repository.api.UserRepository;
import com.ivan.weather.station.persistence.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, UserServiceModel> implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        super(userRepository, modelMapper);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(passwordEncoder.encode(userServiceModel.getPassword()));
        super.save(userServiceModel);
    }

    @Override
    public void activate(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
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
}

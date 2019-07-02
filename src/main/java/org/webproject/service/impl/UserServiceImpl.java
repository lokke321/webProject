package org.webproject.service.impl;

import org.webproject.dto.User;
import org.webproject.entity.UserEntity;
import org.webproject.repository.UserRepository;
import org.webproject.service.AuthenticationService;
import org.webproject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(AuthenticationService authenticationService, UserRepository userRepository, ModelMapper modelMapper) {
        this.authenticationService = authenticationService;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean auth(String login, String password) {
        return authenticationService.authenticate(login, password);
    }

    @Override
    public Collection<User> getAll() {
        Iterable<UserEntity> iterable = userRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(entity -> modelMapper.map(entity, User.class))
                .collect(Collectors.toList());
    }

}

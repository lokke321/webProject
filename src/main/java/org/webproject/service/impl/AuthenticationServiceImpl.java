package org.webproject.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webproject.entity.UserEntity;
import org.webproject.repository.UserRepository;
import org.webproject.service.AuthenticationService;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean authenticate(String login, String password) {
        UserEntity entity = userRepository.findByLogin(login);
        if (entity != null && entity.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}

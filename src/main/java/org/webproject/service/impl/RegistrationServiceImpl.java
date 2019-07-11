package org.webproject.service.impl;


import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webproject.entity.UserEntity;
import org.webproject.repository.UserRepository;
import org.webproject.service.RegistrationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;

    @Autowired
    public RegistrationServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean registration(String login, String password) {
        UserEntity entity = userRepository.findByLogin(login);

        if(entity == null){

            UserEntity newUser = userRepository.save(new UserEntity(login, password));

         return true;

        } return false;

        }


    }


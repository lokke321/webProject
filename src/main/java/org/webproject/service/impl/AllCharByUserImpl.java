package org.webproject.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.webproject.dto.Char;
import org.webproject.dto.UsersChar;
import org.webproject.entity.UserEntity;
import org.webproject.entity.UsersCharEntity;
import org.webproject.repository.UserRepository;
import org.webproject.repository.UsersCharRepository;
import org.webproject.service.AllCharByUser;
import org.webproject.service.AuthorizationSessionService;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AllCharByUserImpl implements AllCharByUser {
 private final AuthorizationSessionService authSessionService;
 private final UserRepository userRepository;
 private final UsersCharRepository usersCharRepository;
 private final ModelMapper modelMapper;

    public AllCharByUserImpl(AuthorizationSessionService authorizationSessionService, UserRepository userRepository, UsersCharRepository usersCharRepository, ModelMapper modelMapper) {
        this.authSessionService = authorizationSessionService;
        this.userRepository = userRepository;
        this.usersCharRepository = usersCharRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Collection<UsersChar> findByUser(String sid) {

        String login = authSessionService.findLoginBySessionId(sid);
        UserEntity userEntity = userRepository.findByLogin(login);

        Iterable<UsersCharEntity> iterable = usersCharRepository.findAllByUser(userEntity);
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(entity -> modelMapper.map(entity, UsersChar.class))
                .collect(Collectors.toList());

    }

    @Override
    public Collection<UsersChar> getAll() {
        Iterable<UsersCharEntity> iterable = usersCharRepository.findAll();
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(entity -> modelMapper.map(entity, UsersChar.class))
                .collect(Collectors.toList());
    }
}

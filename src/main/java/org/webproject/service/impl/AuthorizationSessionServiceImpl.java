package org.webproject.service.impl;



import org.springframework.web.bind.annotation.CookieValue;
import org.webproject.dto.UserSession;
import org.webproject.entity.AuthSessionEntity;
import org.webproject.entity.UserEntity;
import org.webproject.exeption.ChatException;
import org.webproject.repository.AuthorizationSessionRepository;
import org.webproject.repository.UserRepository;
import org.webproject.service.AuthorizationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthorizationSessionServiceImpl implements AuthorizationSessionService {

    private final UserRepository userRepository;
    private final AuthorizationSessionRepository authSessionRepository;
    private final AuthorizationSessionRepository authorizationSessionRepository;
    private final AuthorizationSessionService authSessionService;
    @Autowired
    public AuthorizationSessionServiceImpl(UserRepository userRepository,
                                           AuthorizationSessionRepository authSessionRepository, AuthorizationSessionRepository authorizationSessionRepository, AuthorizationSessionService authSessionService) {
        this.userRepository = userRepository;
        this.authSessionRepository = authSessionRepository;
        this.authorizationSessionRepository = authorizationSessionRepository;
        this.authSessionService = authSessionService;
    }


    @Override
    public UserSession createOrUpdateSession(String login) {
         UserEntity user = userRepository.findByLogin(login);
            if (user == null) {
                throw new RuntimeException();
            }

            Optional<AuthSessionEntity> possibleSession = authSessionRepository.findByLogin(login);
            LocalDateTime expiredDate = LocalDateTime.now().plusDays(1);

            if (possibleSession.isPresent()) {
                AuthSessionEntity entity = possibleSession.get();
                entity.setExpiredDate(expiredDate);
                authSessionRepository.save(entity);

                return new UserSession(entity.getSid(), expiredDate, entity.getUser().getLogin());
            }

            AuthSessionEntity entity = new AuthSessionEntity();
            entity.setUser(user);
            entity.setExpiredDate(expiredDate);
            authSessionRepository.save(entity);

            return new UserSession(entity.getSid(), expiredDate, entity.getUser().getLogin());
        }






    @Override
    public boolean isExpired(String sid) {
        return authSessionRepository.findById(sid) // Optional<AuthSessionEntity>
                .map(session -> session.getExpiredDate().isBefore(LocalDateTime.now())) // if session not null
                .orElse(true);  // if session - null
    }

    @Override
    public String findLoginBySessionId(String sid) {
        return authSessionRepository.findBySid(sid)
                .map(entity -> entity.getUser().getLogin())
                .orElseThrow(ChatException::new);
    }

    @Override
    public void removeSession(String sid) {
        authSessionRepository.deleteById(sid);
    }


    public Integer getUserId(@CookieValue("WC_SESSION") final String sid) {
        String loginBySessionId = authSessionService.findLoginBySessionId(sid);
        UserEntity userEntity = userRepository.findByLogin(loginBySessionId);
        Integer userId = userEntity.getId();
        return userId;
    }



    }
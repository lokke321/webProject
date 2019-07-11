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

    // HashMap<Thread, String>
    //
    private ThreadLocal<String> authenticatedUser = new ThreadLocal<>();

    private final UserRepository userRepository;
    private final AuthorizationSessionRepository authSessionRepository;

    @Autowired
    public AuthorizationSessionServiceImpl(UserRepository userRepository,
                                           AuthorizationSessionRepository authSessionRepository) {
        this.userRepository = userRepository;
        this.authSessionRepository = authSessionRepository;
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
    public Integer findUserIdBySessionId(String sid) {
        return authSessionRepository.findBySid(sid)
                .map(entity -> entity.getUser().getId())
                .orElseThrow(ChatException::new);
    }

    @Override
    public void removeSession(String sid) {
        authSessionRepository.deleteById(sid);
    }

    @Override
    public String getAuthenticatedUser() {
        return authenticatedUser.get();
    }

    @Override
    public void setAuthenticatedUser(String login) {
        authenticatedUser.set(login);
    }

}

package org.webproject.service;

import org.springframework.web.bind.annotation.CookieValue;
import org.webproject.dto.UserSession;

public interface AuthorizationSessionService {

    UserSession createOrUpdateSession(String login);

    boolean isExpired(String sid);

    String findLoginBySessionId(String sid);

    void removeSession(String sid);

    Integer getUserId (String sid);

}
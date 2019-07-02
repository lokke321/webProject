package org.webproject.service;

import org.webproject.dto.UserSession;

public interface AuthorizationSessionService {

    UserSession createOrUpdateSession(String login);

    boolean isExpired(String sid);

}
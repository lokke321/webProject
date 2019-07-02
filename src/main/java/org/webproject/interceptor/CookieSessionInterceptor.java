package org.webproject.interceptor;

import org.webproject.service.AuthorizationSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CookieSessionInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorizationSessionService authSessionService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/mainpage");
            return false;
        }

        // Cookie
        // getName()
        // getValue()
        Optional<Cookie> possibleCookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("WC_SESSION"))
                .findFirst();
        if (!possibleCookie.isPresent()) {
            response.sendRedirect("/mainpage");
            return false;
        }

        if (authSessionService.isExpired(possibleCookie.get().getValue())) {
            response.sendRedirect("/mainpage");
            return false;
        }

        return true;
    }
}
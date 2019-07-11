package org.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.webproject.dto.AuthorizationRequest;
import org.webproject.dto.UserSession;
import org.webproject.service.AuthenticationService;
import org.webproject.service.AuthorizationSessionService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;


@Controller
public class AuthorizationController {

    private final AuthenticationService authService;
    private final AuthorizationSessionService authSessionService;


    @Autowired
    public AuthorizationController(AuthenticationService authService, AuthorizationSessionService authSessionService) {
        this.authService = authService;
        this.authSessionService = authSessionService;
    }

    // GET - page
    // POST - auth user
    @GetMapping("/mainpage")
    public String loginPage(Model model) {
        model.addAttribute("request", new AuthorizationRequest());
        return "mainpage";
    }

    @PostMapping("/mainpage")
    public String login(@ModelAttribute("request") AuthorizationRequest request,
                        BindingResult bindingResult,
                        HttpServletResponse response) {
        boolean result = authService.authenticate(request.getLogin(), request.getPassword());
        if (!result) {
            bindingResult.rejectValue("mainpage", "");
            return "mainpage";
        }

    UserSession session = authSessionService.createOrUpdateSession(request.getLogin());
        response.addCookie(new Cookie("WC_SESSION", session.getSid()));

        return "redirect:/userchars";

}
}
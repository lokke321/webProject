package org.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.webproject.service.RegistrationService;

@Controller
public class RegistrController {
    private final RegistrationService regService;

    @Autowired
    public RegistrController(RegistrationService regService) {
        this.regService = regService;
    }

    @GetMapping("/registry")
    public String registrPage() {
        return "registry";
    }

    @PostMapping("/registry")
    public String registr (@RequestParam("login") final String login,
                           @RequestParam("password")final String password){

        boolean result = regService.registration(login, password);

        return result ? "redirect:/mainpage" : "registry";
    }



}

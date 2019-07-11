package org.webproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.webproject.service.CharactersService;
import org.webproject.service.UserService;

@Controller
public class CharactersController {
    private final UserService userService;
    private final CharactersService charactersService;

    @Autowired
    public CharactersController(UserService userService, CharactersService charactersService) {
        this.userService = userService;
        this.charactersService = charactersService;
    }

    @GetMapping("/chars")
    public String displayAllChars(Model model) {
        model.addAttribute("users", charactersService.getAll());
        return "chars";
    }

    @PostMapping("/registry")
    public String registr (@RequestParam("login") final String login,
                           @RequestParam("password")final String password){



        return result ? "redirect:/mainpage" : "registry";
    }






    }








package org.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.webproject.repository.UsersCharRepository;
import org.webproject.service.AllCharByUser;
import org.webproject.service.CharactersService;

@Controller
public class CharacterController {
    private final CharactersService charactersService;
    private final UsersCharRepository usersCharRepository;
    private final AllCharByUser allCharByUser;

@Autowired
    public CharacterController(CharactersService charactersService, UsersCharRepository usersCharRepository, AllCharByUser allCharByUser) {
        this.charactersService = charactersService;
        this.usersCharRepository = usersCharRepository;
    this.allCharByUser = allCharByUser;
}

    @GetMapping("/userchars")
    public String displayAllChars(@CookieValue("WC_SESSION") final String sid,
                                    Model model) {


        model.addAttribute("users", allCharByUser.findByUser(sid));
        return "userchars";
    }


}

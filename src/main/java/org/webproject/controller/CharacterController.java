package org.webproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webproject.repository.UsersCharRepository;
import org.webproject.service.AllCharByUser;
import org.webproject.service.CharactersService;
import org.webproject.service.CreateUserCharService;

@Controller
public class CharacterController {
    private final CharactersService charactersService;
    private final UsersCharRepository usersCharRepository;
    private final AllCharByUser allCharByUser;
    private final CreateUserCharService createUserCharService;

@Autowired
    public CharacterController(CharactersService charactersService, UsersCharRepository usersCharRepository, AllCharByUser allCharByUser, CreateUserCharService createUserCharService) {
        this.charactersService = charactersService;
        this.usersCharRepository = usersCharRepository;
        this.allCharByUser = allCharByUser;
        this.createUserCharService = createUserCharService;
}

    @GetMapping("/userchars")
    public String displayAllChars(@CookieValue("WC_SESSION") final String sid,
                                    Model model) {
        model.addAttribute("users", allCharByUser.findByUser(sid));
        return "userchars";
    }


    @PostMapping("/userchars")
    public String deleteUserChar (@RequestParam("charName") final String charname){

        createUserCharService.deleteUserChar(charname);
        return "/userchars";

    }



}

package org.webproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webproject.entity.UsersCharEntity;
import org.webproject.repository.UsersCharRepository;
import org.webproject.service.CharactersService;
import org.webproject.service.CreateUserCharService;



@Controller
public class CreateCharactersController {
    private final CharactersService charactersService;
    private final CreateUserCharService createUserCharService;
    private final UsersCharRepository usersCharRepository;

    @Autowired
    public CreateCharactersController(CharactersService charactersService, CreateUserCharService createUserCharService, UsersCharRepository usersCharRepository) {
        this.charactersService = charactersService;
        this.createUserCharService = createUserCharService;

        this.usersCharRepository = usersCharRepository;
    }

    @GetMapping("/chars")
    public String displayAllChars(Model model) {
        model.addAttribute("users", charactersService.getAll());
        return "chars";
    }

    @PostMapping("/chars")
    public String saveUserChar (@CookieValue("WC_SESSION") final String sid,
                                @RequestParam("charName") final String charname,
                                @RequestParam("charId") final Integer charsId)
    {

       UsersCharEntity usersCharEntity = usersCharRepository.findByCharname(charname);

       if (usersCharEntity == null) {
           boolean result = createUserCharService.createUserChar(charsId, sid, charname);
           return "redirect:/userchars";
       }
       else return "redirect:/chars";
    }


    }








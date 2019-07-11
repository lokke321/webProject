package org.webproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.webproject.dto.Char;
import org.webproject.service.CharactersService;
import org.webproject.service.CreateUserCharService;

@Controller
public class CharactersController {
    private final CharactersService charactersService;
    private final CreateUserCharService createUserCharService;

    @Autowired
    public CharactersController(CharactersService charactersService, CreateUserCharService createUserCharService) {
        this.charactersService = charactersService;
        this.createUserCharService = createUserCharService;

    }

    @GetMapping("/chars")
    public String displayAllChars(Model model) {
        model.addAttribute("users", charactersService.getAll());
        return "chars";
    }

    @PostMapping("/saveChars")
    public String saveUserChar (@CookieValue("WC_SESSION") final String sid,
                                @RequestParam("charName") final String charname,
                                @RequestParam("charId") final Integer charsId,
                                Model model){


//    model.addAttribute("users", character);
//    Integer charsId = character.getId();
 //     Integer charsId = 1;

  //  String charName = "Имя";

        Boolean result = createUserCharService.createUserChar(charsId, sid, charname);
        return result ? "redirect:/startgame" : "chars";
    }

    }








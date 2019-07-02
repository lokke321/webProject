package org.webproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.webproject.service.CharactersService;

@Controller
public class CharactersController {

    private final CharactersService charactersService;


  @Autowired
    public CharactersController(CharactersService charactersService){
        this.charactersService = charactersService;

    }

    @GetMapping("/char")
    public String displayAllCharacters(Model model) {
        model.addAttribute("сhar", charactersService.getAll());
        return "char";
    }


}





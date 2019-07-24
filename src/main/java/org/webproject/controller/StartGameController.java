package org.webproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.webproject.service.NpcService;

@Controller
public class StartGameController {
    final NpcService npcService;

    public StartGameController(NpcService npcService) {
        this.npcService = npcService;
    }


    @GetMapping("/startgame")
    public String loginPage(Model model) {
        Integer id = 1;
        model.addAttribute("npcs", npcService.findById(id));
        return "startgame";
    }

    @PostMapping("/startgame")
    public String startGameByUserChars (@RequestParam("charId") final Integer userCharId){

        return "/startgame";
    }


}


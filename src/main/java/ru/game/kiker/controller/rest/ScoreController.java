package ru.game.kiker.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.game.kiker.service.ScoreService;

@RestController
@RequestMapping("${kiker.route.score}")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "/goal", method = RequestMethod.POST)
    public ResponseEntity getService(@RequestParam("idTable")Long id,
                                     @RequestParam("firstTeam")Long firstTeam,
                                     @RequestParam("secondTeam")Long secondTeam) {
        return ResponseEntity.ok(scoreService.updateScore(id, firstTeam, secondTeam));
    }


    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity getPing() {
        return ResponseEntity.ok("PONG!!");
    }
}

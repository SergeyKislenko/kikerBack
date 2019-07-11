package ru.game.kiker.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.game.kiker.service.GameService;

import java.util.Map;

@RestController
@RequestMapping("${kiker.route.game}")

public class GameController {

    @Autowired
    private GameService gameService;

    @RequestMapping(value = "/scoreActiveGame", method = RequestMethod.GET)
    public ResponseEntity getScore(@RequestParam("idTable") Long idTable) {
        Map score = gameService.scoreActiveGame(idTable);
        return ResponseEntity.ok(score);
    }
}

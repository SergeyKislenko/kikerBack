package ru.game.kiker.service;

import ru.game.kiker.model.entity.OnlineGame;

public interface ScoreService {
    OnlineGame findOnlineGameById(Long id);

    Boolean updateScore(Long id, Long firstTeam, Long secondTeam, String type);

}

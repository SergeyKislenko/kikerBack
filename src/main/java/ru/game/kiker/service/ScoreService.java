package ru.game.kiker.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;
import ru.game.kiker.model.entity.OnlineGame;

public interface ScoreService {
    OnlineGame findOnlineGameById(Long id);

    QueryDocumentSnapshot findDocById(Long id);

    Boolean updateScore(Long id, Integer firstTeam, Integer secondTeam, String type);

}

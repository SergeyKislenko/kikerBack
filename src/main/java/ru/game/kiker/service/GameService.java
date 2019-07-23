package ru.game.kiker.service;

import com.google.cloud.firestore.QueryDocumentSnapshot;

import java.util.Map;

public interface GameService {
    Map scoreActiveGame(Long idTable);
    boolean sendGameToHistory(Long idTable);
    boolean updateScoreTeam(String name, QueryDocumentSnapshot document, Long point);
}

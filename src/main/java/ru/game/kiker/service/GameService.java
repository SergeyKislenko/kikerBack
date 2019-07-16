package ru.game.kiker.service;

import java.util.Map;

public interface GameService {
    Map scoreActiveGame(Long idTable);
    boolean sendGameToHistory(Long idTable);
}

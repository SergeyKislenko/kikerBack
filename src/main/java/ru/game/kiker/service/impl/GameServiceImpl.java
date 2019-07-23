package ru.game.kiker.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.game.kiker.configurations.DataBaseConfig;
import ru.game.kiker.exceptions.GameServiceException;
import ru.game.kiker.model.entity.OnlineGame;
import ru.game.kiker.service.GameService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class GameServiceImpl implements GameService {

    Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private DataBaseConfig dbConfig;

    final private Long WIN_POINTS = 3L;
    final private Long MIDL_POINTS = 1L;

    @Override
    public Map scoreActiveGame(Long idTable) {
        Map<String, Object> score = new HashMap<>();
        Firestore db = dbConfig.initFirebase();
        try {
            Query onlineGame = db.collection("online").whereEqualTo("idTable", idTable);
            ApiFuture<QuerySnapshot> querySnapshot = onlineGame.get();
            if (!querySnapshot.get().getDocuments().isEmpty()) {
                QueryDocumentSnapshot document = querySnapshot.get().getDocuments().get(0);
                if (document.getBoolean("status")) {
                    score.put("firstScore", document.get("firstScore"));
                    score.put("secondScore", document.get("secondScore"));
                } else {
                    score.put("The game is not active", document.getId());
                    logger.info("The game " + document.getId() + " is not active");
                }

            } else {
                throw new GameServiceException("Table with id " + idTable + " doesn`t exist");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return score;
    }

    @Override
    public boolean sendGameToHistory(Long idTable) {
        Firestore db = dbConfig.initFirebase();
        try {
            Query onlineGame = db.collection("online").whereEqualTo("idTable", idTable);
            ApiFuture<QuerySnapshot> querySnapshot = onlineGame.get();
            if (!querySnapshot.get().getDocuments().isEmpty()) {
                QueryDocumentSnapshot document = querySnapshot.get().getDocuments().get(0);
                DocumentReference historyGames = db.collection("historyGames").document(document.getId());
                ApiFuture<WriteResult> result = historyGames.set(document.getData());
                logger.info("Game sended " + document.getId() + " to history in" + result.get().getUpdateTime());

                if (document.getLong("firstScore") > document.getLong("secondScore")) {
                    updateScoreTeam("firstTeam", document, WIN_POINTS);
                } else if (document.getLong("firstScore") < document.getLong("secondScore")) {
                    updateScoreTeam("secondTeam", document, WIN_POINTS);
                } else if (document.getLong("firstScore") == document.getLong("secondScore")) {
                    updateScoreTeam("firstTeam", document, MIDL_POINTS);
                    updateScoreTeam("secondTeam", document, MIDL_POINTS);
                }

                db.collection("online").document(document.getId()).delete();
                logger.info("Game " + document.getId() + " deleted in" + result.get().getUpdateTime());
                db.collection("online").document().set(new OnlineGame().createEmptyGame(idTable));
                return true;
            } else {
                throw new GameServiceException("Table with id " + idTable + " doesn`t exist");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateScoreTeam(String name, QueryDocumentSnapshot document, Long point) {
        Firestore db = dbConfig.initFirebase();
        ArrayList<String> list = (ArrayList<String>) document.get(name);
        if(document.get(name) != null){
            for (String player : list) {
                try {
                    DocumentReference p = db.collection("top").document(player);
                    if (p.get().get().getLong("score") != null) {
                        Long score = p.get().get().getLong("score") + point;
                        Map<String, Object> val = p.get().get().getData();
                        val.put("score", score);
                        p.set(val);
                        logger.info(point + " point for player " + player);
                        return true;
                    } else {
                        throw new GameServiceException("User with id " + player + " doesn`t exist");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }


}

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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class GameServiceImpl implements GameService {

    Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private DataBaseConfig dbConfig;

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
                db.collection("online").document(document.getId()).delete();
                logger.info("Game " + document.getId() + " deleted in" + result.get().getUpdateTime());
                db.collection("online").document().set(new OnlineGame().createEmptyGame(idTable));
                return false;
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
}

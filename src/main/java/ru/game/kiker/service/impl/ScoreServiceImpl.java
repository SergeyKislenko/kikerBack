package ru.game.kiker.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.game.kiker.configurations.DataBaseConfig;
import ru.game.kiker.exceptions.GameServiceException;
import ru.game.kiker.model.entity.OnlineGame;
import ru.game.kiker.model.entity.TimeLine;
import ru.game.kiker.service.ScoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static ru.game.kiker.utils.PrepareTimeLine.prepareTimeLine;

@Component
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private DataBaseConfig dbConfig;


    @Override
    public OnlineGame findOnlineGameById(Long id) {
        Firestore db = dbConfig.initFirebase();
        try {
            Query temp = db.collection("online").whereEqualTo("idTable", id);
            ApiFuture<QuerySnapshot> querySnapshot = temp.get();
            QueryDocumentSnapshot document = querySnapshot.get().getDocuments().get(0);
            if (document.exists()) {
                OnlineGame game = new OnlineGame(document.get("firstScore"),
                        document.get("secondScore"),
                        document.getBoolean("status"),
                        document.getLong("idTable"),
                        document.get("timeline"));
                return game;
            } else {
                throw new GameServiceException("Table with id " + id + " doesn`t exist");
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateScore(Long id, Integer firstTeam, Integer secondTeam, String type) {
        Firestore db = dbConfig.initFirebase();
        OnlineGame game = findOnlineGameById(id);
        if (game != null) {
            try {
                DocumentReference docRef = db.collection("online").document("gameTemp");
                ArrayList<TimeLine> timeLine = prepareTimeLine(game, firstTeam, secondTeam, type);
                game.setTimeline(timeLine);
                game.setFirstScore(firstTeam);
                game.setSecondScore(secondTeam);
                ApiFuture<WriteResult> result = docRef.set(game);
                System.out.println("Update time : " + result.get().getUpdateTime());
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

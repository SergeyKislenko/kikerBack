package ru.game.kiker.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.game.kiker.configurations.DataBaseConfig;
import ru.game.kiker.model.entity.OnlineGame;
import ru.game.kiker.model.entity.TimeLine;
import ru.game.kiker.service.ScoreService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Component
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private DataBaseConfig dbConfig;


    @Override
    public OnlineGame findOnlineGameById(Long id) {
        Firestore db = dbConfig.initFirebase();
        try {
            ApiFuture<QuerySnapshot> query = db.collection("online").get();
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                if (document.getLong("idTable") != null) {
                    if (document.getLong("idTable").equals(id)) {
                        OnlineGame game = new OnlineGame(document.get("firstTeam"),
                                document.get("secondTeam"),
                                document.getBoolean("status"),
                                document.getLong("idTable"),
                                document.get("timeLine"));
                        return game;
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateScore(Long id, Long firstTeam, Long secondTeam, String type) {
        Firestore db = dbConfig.initFirebase();
        OnlineGame game = findOnlineGameById(id);
        if (game != null) {
            try {
                DocumentReference docRef = db.collection("online").document("gameTemp");

                if(game.getTimeline()==null){
                    ArrayList<TimeLine> timeLine = new ArrayList<>();
                }

                game.getFirstTeam().setScore(firstTeam);
                game.getSecondTeam().setScore(secondTeam);
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

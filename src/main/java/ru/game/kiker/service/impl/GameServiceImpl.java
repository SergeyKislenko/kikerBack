package ru.game.kiker.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.game.kiker.configurations.DataBaseConfig;
import ru.game.kiker.exceptions.GameServiceException;
import ru.game.kiker.service.GameService;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class GameServiceImpl implements GameService {
    @Autowired
    private DataBaseConfig dbConfig;

    @Override
    public Map scoreActiveGame(Long idTable) {
        Map<String, Object> score = new HashMap<>();
        Firestore db = dbConfig.initFirebase();
        try {
            Query temp = db.collection("online").whereEqualTo("idTable", idTable);
            ApiFuture<QuerySnapshot> querySnapshot = temp.get();
            QueryDocumentSnapshot document = null;
            if (!querySnapshot.get().getDocuments().isEmpty()) {
                document = querySnapshot.get().getDocuments().get(0);
                if (document.getBoolean("status")) {
                    score.put("firstScore",document.get("firstScore"));
                    score.put("secondScore", document.get("secondScore"));
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
}

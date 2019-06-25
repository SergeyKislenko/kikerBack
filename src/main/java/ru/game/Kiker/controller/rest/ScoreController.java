package ru.game.Kiker.controller.rest;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.game.Kiker.configurations.DataBaseConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("${kiker.route.score}")
public class ScoreController {

    @Autowired
    private DataBaseConfig dbConfig;


    @RequestMapping(value = "/goal", method = RequestMethod.GET)
    public ResponseEntity getService() {
        Firestore db = dbConfig.initFirebase();

        try {
            DocumentReference docRef = db.collection("items").document("superGOAL");
            Map<String, Object> data = new HashMap<>();
            data.put("born", "1111");
            data.put("firs", "ZOHN");
            data.put("last", "FUCKл");
            ApiFuture<WriteResult> result = docRef.set(data);
            System.out.println("Update time : " + result.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("goal incremented");
    }
}

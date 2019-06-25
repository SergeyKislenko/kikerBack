package ru.game.kiker.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
public class DataBaseConfig {

    @Bean
    public Firestore initFirebase() {
        Firestore db = null;
        try {
            FileInputStream serviceAccount = new FileInputStream("key.json");
            FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://kicker-87cff.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(firebaseOptions);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            db = FirestoreClient.getFirestore();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }
}

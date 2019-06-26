package ru.game.kiker.utils;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.game.kiker.model.json.ConfigFireBase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Component
public class PrepareConfigFile {

    @Value("${firebase.type}")
    private String type;

    @Value("${firebase.projectId}")
    private String projectId;

    @Value("${firebase.privateKeyId}")
    private String privateKeyId;

    @Value("-----BEGIN PRIVATE KEY-----\n" + "${firebase.privateKey}" + "\n-----END PRIVATE KEY-----\n")
    private String privateKey;

    @Value("${firebase.clientEmail}")
    private String clientEmail;

    @Value("${firebase.clientId}")
    private String clientId;

    @Value("${firebase.authUri}")
    private String authUri;

    @Value("${firebase.tokenUri}")
    private String tokenUri;

    @Value("${firebase.authProvider}")
    private String authProvider;

    @Value("${firebase.client}")
    private String client;

    public InputStream prepareConfig() {
        ConfigFireBase configFireBase = new ConfigFireBase(type, projectId, privateKeyId, privateKey, clientEmail, clientId, authUri, tokenUri, authProvider, client);
        Gson gson = new Gson();
        String json = gson.toJson(configFireBase);
        InputStream targetStream = new ByteArrayInputStream(json.getBytes());
        return targetStream;
    }
}

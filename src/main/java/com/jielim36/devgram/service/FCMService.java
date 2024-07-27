package com.jielim36.devgram.service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jielim36.devgram.entity.FirebaseNotificationRequest;
import com.jielim36.devgram.entity.FirebaseUserTokenEntity;
import com.jielim36.devgram.mapper.FirebaseUserTokenMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

@Service
public class FCMService {

    private final FirebaseApp firebaseApp;
    private final FirebaseUserTokenMapper firebaseUserTokenMapper;

    @Value("${myapp.url}")
    private String websiteUrl;

    private Logger logger = LoggerFactory.getLogger(FCMService.class);

    public FCMService(FirebaseApp firebaseApp, FirebaseUserTokenMapper firebaseUserTokenMapper) {
        this.firebaseApp = firebaseApp;
        this.firebaseUserTokenMapper = firebaseUserTokenMapper;
    }

    public void sendMessageToToken(FirebaseNotificationRequest request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
//        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageToToken(FirebaseNotificationRequest request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }

    private Message.Builder getPreconfiguredMessageBuilder(FirebaseNotificationRequest request) {
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        WebpushConfig webpushConfig = WebpushConfig.builder()
                .setFcmOptions(WebpushFcmOptions.builder()
                        .setLink(request.getRedirect_url())
                        .build())
                .build();
        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .setImage(request.getImage())
                .build();

        return Message.builder()
                .setApnsConfig(apnsConfig)
                .setAndroidConfig(androidConfig)
                .setNotification(notification)
                .setWebpushConfig(webpushConfig);
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build()).build();
    }

    public boolean saveToken(FirebaseUserTokenEntity userToken) {
        FirebaseUserTokenEntity tokenByUserId = firebaseUserTokenMapper.getTokenByUserId(userToken.getUser_id());
        int result;
        if(tokenByUserId != null) {
            if(tokenByUserId.getToken().equals(userToken.getToken())){
                return true;
            }
            result = firebaseUserTokenMapper.updateToken(userToken);
        } else {
            result = firebaseUserTokenMapper.saveToken(userToken);
        }
        return result > 0;
    }

    public void sendChatNotificationToUser(String sender_avatar_url, Long receiver_user_id, String title, String body) {
        FirebaseUserTokenEntity userToken = firebaseUserTokenMapper.getTokenByUserId(receiver_user_id);
        if(userToken == null) return;

        String redirect_url = websiteUrl + "/chat/" + receiver_user_id;

        FirebaseNotificationRequest request = new FirebaseNotificationRequest();
        request.setTitle(title);
        request.setBody(body);
        request.setToken(userToken.getToken());
        request.setImage(sender_avatar_url);
        request.setRedirect_url(redirect_url);

        try {
            sendMessageToToken(request);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

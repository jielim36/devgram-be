package com.jielim36.devgram.config;

import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PusherConfig {

    @Value("${pusher.app_id}")
    private String PUSHER_APP_ID;

    @Value("${pusher.key}")
    private String PUSHER_KEY;

    @Value("${pusher.secret}")
    private String PUSHER_SECRET;

    @Value("${pusher.cluster}")
    private String PUSHER_CLUSTER;

    @Bean
    public Pusher pusher() {
        Pusher pusher = new Pusher(PUSHER_APP_ID, PUSHER_KEY, PUSHER_SECRET);
        pusher.setCluster(PUSHER_CLUSTER);
        pusher.setEncrypted(true);
        return pusher;
    }

}

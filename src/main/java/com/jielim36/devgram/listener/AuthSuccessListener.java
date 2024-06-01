package com.jielim36.devgram.listener;

import com.jielim36.devgram.service.AuthService;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;

@Component
public class AuthSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final AuthService authService;

    public AuthSuccessListener(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        System.out.println("AuthSuccessListener");



        if (event.getAuthentication() instanceof OAuth2AuthenticationToken) {
            System.out.println("IS OAuth2AuthenticationToken");
            OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) event.getAuthentication().getPrincipal();
            String providerId = ((OAuth2AuthenticationToken) event.getAuthentication()).getAuthorizedClientRegistrationId();

            System.out.println(principal.toString());

            //if user not exists, create new user
            if (!authService.checkUserExists(providerId, principal)) {
                System.out.println("GO to register");
                //create new user
                authService.register(providerId, principal);
            }
        } else {
            System.out.println("NOT OAuth2AuthenticationToken");
        }
    }
}

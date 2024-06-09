package com.jielim36.devgram.CustomAnnotation.UserIdRequired;

import com.jielim36.devgram.enums.ResultCode;
import com.jielim36.devgram.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class AuthAOP {

    @Autowired
    private AuthService authService;


    @Before("@annotation(UserIdRequired) || @within(UserIdRequired)")
    public void getUserId(JoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            String providerId = ((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId();
            OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();
            Long userId = authService.getUserIdByProviderId(providerId, principal);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                request.setAttribute("userId", userId);
                return;
            }

        }

        throw new UnauthorizedException(ResultCode.UNAUTHORIZED.getResultMsg());
    }

    private HttpServletRequest getRequestFromArgs(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                return (HttpServletRequest) arg;
            }
        }
        return null;
    }

    public class UnauthorizedException extends RuntimeException {
        public UnauthorizedException(String message) {
            super(message);
        }
    }

}

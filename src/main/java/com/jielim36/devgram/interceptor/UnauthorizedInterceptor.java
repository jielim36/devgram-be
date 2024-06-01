package com.jielim36.devgram.interceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UnauthorizedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (response.getStatus() == HttpServletResponse.SC_UNAUTHORIZED) {
            System.out.println("Unauthorized request intercepted: " + request.getRequestURI());
            return false;
        }
        return true;
    }
}

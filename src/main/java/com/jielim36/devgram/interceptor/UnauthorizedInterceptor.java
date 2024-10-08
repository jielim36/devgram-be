package com.jielim36.devgram.interceptor;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UnauthorizedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            return true;
        }


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("JSESSIONID".equals(cookie.getName())) {
                    //check cookie valid
                    return true;
                }
            }
        }

//        ResultResponse resultResponse = ResultResponse.failure(ResultCode.UNAUTHORIZED);
//        String json = new ObjectMapper().writeValueAsString(resultResponse);
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().write(json);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JSessionID cookie is missing");
        return false;
    }
}

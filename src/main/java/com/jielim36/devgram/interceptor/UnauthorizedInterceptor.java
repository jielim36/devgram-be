package com.jielim36.devgram.interceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jielim36.devgram.common.Response.ResultCode;
import com.jielim36.devgram.common.Response.ResultResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UnauthorizedInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

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

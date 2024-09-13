package com.sysone.ddogdog.common.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
        throws IOException, ServletException {
        String uri = request.getRequestURI();

        if (uri.startsWith("/v1/owners")) {
            //미로그인자 접근권한 페이지 접근 시 알럿띄우기
            response.sendRedirect("/v1/owners/login?alert=true");
        } else {
            response.sendRedirect("/v1/customers/oauth");
        }
    }
}
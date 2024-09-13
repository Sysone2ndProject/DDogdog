package com.sysone.ddogdog.common.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
        String uri = request.getRequestURI();
        if (uri.startsWith("/v1/owners")) {
            response.sendRedirect("/v1/owners/login?logout=true");
        } else {
            response.sendRedirect("/v1/customers/oauth");
        }
    }
}
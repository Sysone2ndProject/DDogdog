package com.sysone.ddogdog.common.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * 로그인한 유저 ROLE 체크 후 불충분 시 예외 처리하는 부분 ex) 세션 만료
 */

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
        throws IOException, ServletException {
        String uri = request.getRequestURI();
        // 세션 만료시 로그인 창으로 이동시키는 용도
        if (uri.startsWith("/v1/owners")) {
            response.sendRedirect("/v1/owners/login?logout=true");
        } else {
            response.sendRedirect("/v1/customers?alert=true");
        }
    }
}
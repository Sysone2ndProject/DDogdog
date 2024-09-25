package com.sysone.ddogdog.common.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final String KAKAO_LOGOUT_URL = "https://kauth.kakao.com/oauth/logout";

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUri;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        if (authentication != null && authentication.getPrincipal() instanceof OAuth2User) {
            // OAuth2 로그아웃 처리
            System.out.println("카카오 로그아웃 진입");
            String logoutUrl = KAKAO_LOGOUT_URL
                + "?client_id=" + kakaoClientId  // 카카오 앱의 클라이언트 ID
                + "&logout_redirect_uri=" + "http://localhost:8080/v1/customers"; // 로그아웃 후 리다이렉트할 URL

            response.sendRedirect(logoutUrl);  // 카카오 로그아웃 요청으로 리다이렉트
        } else {
            // Form 로그아웃 처리
            System.out.println("폼 로그아웃 진입");
            response.sendRedirect("/v1/owners"); // 폼 로그아웃 후 리다이렉트할 URL
        }
    }
}
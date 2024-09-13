package com.sysone.ddogdog.owner.auth.util;

import com.sysone.ddogdog.owner.auth.model.OwnerDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        log.info("성공");

        OwnerDetails onwer = (OwnerDetails) authentication.getPrincipal();
        //현재 테스트용으로 권한이 필요한 유저 페이지 이동
        // TODO: 9/13/24 메인페이지로 이동 및 로그인에 따른 헤더 정보 변경
        response.sendRedirect("/v1/owners/user");
    }
}
package com.sysone.ddogdog.common.config.oauth;

import com.sysone.ddogdog.customer.auth.mapper.KakaoMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Oauth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final KakaoMapper kakaoMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String id = principal.getOAuth2UserInfo().getProviderId();

        Long result = kakaoMapper.existsAddressIdById(Long.parseLong(id));
        System.out.println(result);
        if (result == null) {
            log.info("주소 정보 없음 -> 추가정보 입력진행");
            getRedirectStrategy().sendRedirect(request, response, "/v1/customers/signup/location");
            return;
        } else {
            log.info("로그인 정보 세션에 담아 반환");
            getRedirectStrategy().sendRedirect(request, response, "/v1/customers/mainpage");
            return;
        }
    }
}

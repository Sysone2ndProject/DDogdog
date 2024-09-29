package com.sysone.ddogdog.customer.auth.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class KakaoViewController {

    private final KakaoService kakaoService;

    // 로그인 페이지
    @GetMapping("/loginpage")
    public String loginPage() {
        return "customer/auth/login";
    }

    // 주소 입력 페이지
    @GetMapping("/signup/location")
    public String locationPage(@AuthenticationPrincipal PrincipalDetails principalDetails,
                               Model model) {
        System.out.println(principalDetails.getOAuth2UserInfo().getProviderId());
        model.addAttribute("id",
                Long.parseLong(principalDetails.getOAuth2UserInfo().getProviderId()));
        return "customer/auth/location";
    }
}
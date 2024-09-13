package com.sysone.ddogdog.customer.auth.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class KakaoController {

    private final KakaoService kakaoService;

    // 로그인 페이지
    @GetMapping("/v1/customers/loginpage")
    public String loginPage() {
        return "customer/auth/login";
    }

    // 주소 입력 페이지
    @GetMapping("/v1/customers/signup/location")
    public String locationPage(@AuthenticationPrincipal PrincipalDetails principalDetails,
        Model model) {
        System.out.println(principalDetails.getOAuth2UserInfo().getProviderId());
        model.addAttribute("id",
            Long.parseLong(principalDetails.getOAuth2UserInfo().getProviderId()));
        return "customer/auth/location";
    }

    //회원 가입 요청
    @PostMapping("/v1/customers/signup")
    public ResponseEntity<Void> signUp(@RequestBody AddressDTO addressDTO) {
        log.info("signup test");
        log.info(addressDTO.toString());
        kakaoService.saveAddress(addressDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/customers/mainpage")
    public String mainPage(@AuthenticationPrincipal PrincipalDetails details, Model model){
        //권한체크
        for (GrantedAuthority authority : details.getAuthorities()) {
            System.out.println(authority.getAuthority());
        }
        model.addAttribute("auth",details);
        return "customer/auth/home";
    }
}
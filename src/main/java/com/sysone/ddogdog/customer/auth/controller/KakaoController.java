package com.sysone.ddogdog.customer.auth.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.auth.model.AddressDTO;
import com.sysone.ddogdog.customer.auth.model.RequestAddressDTO;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/customers")
public class KakaoController {

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

    //회원 가입 요청
    //TODO : api 요청 RestController 분리 예정
    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@RequestBody AddressDTO addressDTO) {
        log.info("signup test");
        log.info(addressDTO.toString());
        kakaoService.saveAddress(addressDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/location")
    public ResponseEntity<Void> updateLocation(@RequestBody RequestAddressDTO requestAddressDTO) {
        log.info("signup test");
        log.info(requestAddressDTO.toString());
        kakaoService.updateAddress(requestAddressDTO);
        return ResponseEntity.ok().build();
    }

}
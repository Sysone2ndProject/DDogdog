package com.sysone.ddogdog.customer.myPage.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import com.sysone.ddogdog.customer.myPage.service.MyPageService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers/myPage")
public class MyPageController {

    private final MyPageService myPageService;
//    private final KakaoService kakaoService;

//    @Value("${kakao.map.api-key}")
//    private String kakaoJsId;
//
//    @GetMapping
//    public String myPage(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
//        Long id = Long.parseLong(principalDetails.getUsername());
//        CustomerDTO customerDTO = kakaoService.findUserById(id).get();
//        log.info("test"+customerDTO.toString());
//        model.addAttribute("CustomerInfo",customerDTO);
//        model.addAttribute("apiKey",kakaoJsId);
//        return "customer/myPage/myPage";
//    }

    @GetMapping("/location")
    public ResponseEntity<?> getCustomerAddress(@RequestParam Long addressId){
        String fullAddress = myPageService.findFullAddressById(addressId);
        log.info(fullAddress);
        return ResponseEntity.ok(fullAddress);
    }
}

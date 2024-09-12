package com.sysone.ddogdog.customer.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysone.ddogdog.customer.auth.model.AddressCustomerDTO;
import com.sysone.ddogdog.customer.auth.model.CustomerDTO;
import com.sysone.ddogdog.customer.auth.model.KakaoProfile;
import com.sysone.ddogdog.customer.auth.service.KakaoService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class KakaoController {

    private final KakaoService kakaoService;

    // 로그인 페이지
    @GetMapping("/v1/oauth/loginpage")
    public String loginPage() {
        return "customer/auth/login";
    }

    // 로그인 진행
    @RequestMapping("/v1/oauth/login")
    public String kakaoLogin(@RequestParam String code, Model model)
        throws JsonProcessingException {
        // 1. 인가 코드 받기 (@RequestParam String code)
        // 2. 토큰 받기
        String accessToken = kakaoService.getAccessToken(code);
        // 3. 사용자 정보 받기
        KakaoProfile kakaoProfile = kakaoService.getUserInfo(accessToken);
        // 4. 체크 후 로그인
        Optional<CustomerDTO> customerOpt  = kakaoService.checkIfUserExists(kakaoProfile.getId());
        if (customerOpt.isPresent()) { //유저정보 존재할경우
            model.addAttribute("customerDTO", customerOpt.get());
            System.out.println("유저정보 존재");
            //TODO : security 이용하여 로그인 후 세션관리
            return "customerDetails"; //유저객체들고 로그인관련으로 이동
        } else { //유저정보없을경우
            System.out.println("회원가입 진행");
            model.addAttribute("kakaoProfile", kakaoProfile);
            return "forward:/v1/customers/signup/location"; // 카카오객체정보들고 주소정보받으러 이동
        }
    }

    // 주소 입력 페이지
    @GetMapping("/v1/customers/signup/location")
    public String locationPage(){
        return "customer/auth/location";
    }

    // 회원 가입 요청
    @PostMapping("/v1/customers/signup")
    public ResponseEntity<Void> signUp(@RequestBody AddressCustomerDTO addressCustomerDTO){
        System.out.println("test");
        System.out.println(addressCustomerDTO.toString());
        kakaoService.saveCustomers(addressCustomerDTO);
        return ResponseEntity.ok().build();
    }
}
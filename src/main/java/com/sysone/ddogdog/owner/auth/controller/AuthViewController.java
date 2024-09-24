package com.sysone.ddogdog.owner.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/owners")
@RequiredArgsConstructor
public class AuthViewController {

    @GetMapping("/signup")
    public String signUpForm() {
        return "owner/signUp";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "owner/login";
    }

}
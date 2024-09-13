package com.sysone.ddogdog.owner.auth.controller;

import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import com.sysone.ddogdog.owner.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/owners")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/signup")
    public String signUpForm() {
        return "owner/signUp";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> findId(@PathVariable String id) {
        return ResponseEntity.ok(authService.findById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@ModelAttribute AuthDTO authDTO) {
        authService.signUpOwner(authDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public String loginForm() {
        return "owner/login";
    }
}

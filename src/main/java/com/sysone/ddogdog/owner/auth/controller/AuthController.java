package com.sysone.ddogdog.owner.auth.controller;

import com.sysone.ddogdog.owner.auth.model.AuthDTO;
import com.sysone.ddogdog.owner.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/owners")
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> findId(@PathVariable String id) {
        return ResponseEntity.ok(authService.findById(id));
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signUp(@ModelAttribute AuthDTO authDTO) {
        authService.signUpOwner(authDTO);
        return ResponseEntity.ok().build();
    }

}

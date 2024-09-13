package com.sysone.ddogdog.owner.user.controller;

import com.sysone.ddogdog.owner.auth.model.OwnerDetails;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/owners/user")
public class UserController {

    @GetMapping()
    public String userForm(@AuthenticationPrincipal OwnerDetails user, HttpServletRequest httpServletRequest){
        return "owner/mypage";
    }
}
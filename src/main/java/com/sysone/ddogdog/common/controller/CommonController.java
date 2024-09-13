package com.sysone.ddogdog.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1")
public class CommonController {

    @GetMapping("/owners")
    public String ownerMain() {
        return "owner/main";
    }
}
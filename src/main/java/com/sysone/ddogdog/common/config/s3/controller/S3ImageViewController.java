package com.sysone.ddogdog.common.config.s3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class S3ImageViewController {

    @GetMapping("/s3/test")
    public String s3Test(){
        return "common/s3/testPage";
    }
}

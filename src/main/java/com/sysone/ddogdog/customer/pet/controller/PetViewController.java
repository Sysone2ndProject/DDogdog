package com.sysone.ddogdog.customer.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/customers/pet")
public class PetViewController {

    @GetMapping
    public String petInfo(){
        return "customer/pet";
    }

    @GetMapping("/add")
    public String petAdd(){
        return "customer/petAdd";
    }
}

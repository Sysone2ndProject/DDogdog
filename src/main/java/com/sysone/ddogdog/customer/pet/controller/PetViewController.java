package com.sysone.ddogdog.customer.pet.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/customers/myPage")
public class PetViewController {

    @GetMapping("/pets")
    public String petInfo(){
        return "customer/pets";
    }

    @GetMapping("/pets/add")
    public String petAdd(){
        return "customer/petAdd";
    }
}

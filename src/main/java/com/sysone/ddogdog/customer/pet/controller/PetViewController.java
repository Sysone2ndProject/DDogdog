package com.sysone.ddogdog.customer.pet.controller;

import com.sysone.ddogdog.customer.pet.model.ResponsePetDetailsDTO;
import com.sysone.ddogdog.customer.pet.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/customers/pets")
@RequiredArgsConstructor
public class PetViewController {

    private final PetService petService;

    @GetMapping("/add")
    public String petAdd() {
        return "customer/petAdd";
    }

    @GetMapping("/{petId}")
    public String viewPetDetails(@PathVariable Long petId, Model model) {
        ResponsePetDetailsDTO pet = petService.findPetById(petId);
        model.addAttribute("pet", pet);
        return "customer/petDetails";
    }
}

package com.sysone.ddogdog.customer.pet.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.pet.model.PetDTO;
import com.sysone.ddogdog.customer.pet.service.PetService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping
    public String petsInfo(@AuthenticationPrincipal PrincipalDetails user, Model model) {
        List<PetDTO> pets = petService.findAllPetsById(Long.parseLong(user.getUsername()));
        model.addAttribute("pets", pets);
        return "customer/pets";
    }

    @GetMapping("/add")
    public String petAdd() {
        return "customer/petAdd";
    }

    @GetMapping("/{petId}")
    public String viewPetDetails(@PathVariable Long petId, Model model) {
        PetDTO pet = petService.findPetById(petId);
        model.addAttribute("pet", pet);
        return "customer/petDetails";
    }
}

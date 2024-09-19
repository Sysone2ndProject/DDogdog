package com.sysone.ddogdog.customer.pet.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.pet.model.PetSpeciesDTO;
import com.sysone.ddogdog.customer.pet.model.RequestPetDTO;
import com.sysone.ddogdog.customer.pet.service.PetService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers/pets")
public class PetController {

    private final PetService petService;

    @GetMapping("/species")
    public ResponseEntity<List<PetSpeciesDTO>> species(
        @RequestParam(value = "query", required = false, defaultValue = "") String query) {
        List<PetSpeciesDTO> species = petService.findSpecies(query);
        return ResponseEntity.ok(species);
    }

    @PostMapping("/species/add")
    public ResponseEntity<Long> speciesAdd(@RequestBody Map<String, String> data) {
        Long id = petService.saveSpecies(data.get("query"));
        return ResponseEntity.ok(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createPet(@AuthenticationPrincipal PrincipalDetails user,
        @ModelAttribute RequestPetDTO requestPetDTO) {
        petService.savePet(user.getUsername(), requestPetDTO);
        return ResponseEntity.ok().build();
    }


}

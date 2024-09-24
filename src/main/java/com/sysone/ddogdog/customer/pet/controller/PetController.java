package com.sysone.ddogdog.customer.pet.controller;

import com.sysone.ddogdog.common.config.oauth.PrincipalDetails;
import com.sysone.ddogdog.customer.pet.model.PetDTO;
import com.sysone.ddogdog.customer.pet.model.PetSpeciesDTO;
import com.sysone.ddogdog.customer.pet.model.RequestPetDTO;
import com.sysone.ddogdog.customer.pet.model.ResponsePetDTO;
import com.sysone.ddogdog.customer.pet.service.PetService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers/pets")
public class PetController {

    private final PetService petService;

    @GetMapping
    public ResponseEntity<List<ResponsePetDTO>> petsInfo(String customerId) {
        List<ResponsePetDTO> pets = petService.findAllPetsById(Long.parseLong(customerId));
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/species")
    public ResponseEntity<List<PetSpeciesDTO>> species(
        @RequestParam(value = "query", required = false, defaultValue = "") String query) {
        List<PetSpeciesDTO> species = petService.findSpecies(query);
        return ResponseEntity.ok(species);
    }

    @PostMapping("/species")
    public ResponseEntity<Long> speciesAdd(@RequestBody Map<String, String> data) {
        Long id = petService.saveSpecies(data.get("query"));
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createPet(@AuthenticationPrincipal PrincipalDetails user,
        @ModelAttribute RequestPetDTO requestPetDTO) {
        petService.savePet(user.getUsername(), requestPetDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updatePet(@ModelAttribute RequestPetDTO requestPetDTO) {
        System.out.println("업데이트 진입");
        petService.updatePet(requestPetDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

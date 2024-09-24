package com.sysone.ddogdog.customer.pet.service;

import com.sysone.ddogdog.common.config.s3.service.S3ImageService;
import com.sysone.ddogdog.customer.pet.mapper.PetMapper;
import com.sysone.ddogdog.customer.pet.model.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PetService {

    private final PetMapper petMapper;
    private final S3ImageService s3ImageService;

    public List<PetSpeciesDTO> findSpecies(String query) {
        if (query.isEmpty()) {
            return petMapper.findAll();
        } else {
            return petMapper.findSpeciesById(query);
        }
    }

    @Transactional
    public Long saveSpecies(String query) {
        PetSpeciesDTO petSpeciesDTO = new PetSpeciesDTO();
        petSpeciesDTO.setSpecies(query);
        petMapper.saveSpecies(petSpeciesDTO);
        return petSpeciesDTO.getId();
    }

    @Transactional
    public void savePet(String userId, RequestPetDTO requestPetDTO) {
        String petImageUrl = s3ImageService.upload(requestPetDTO.getPetImage());
        PetDTO petDTO = PetDTO.fromRequestPetDTO(Long.parseLong(userId), petImageUrl,
            requestPetDTO);
        petMapper.savePet(petDTO);
    }

    @Transactional
    public void updatePet(RequestPetDTO requestPetDTO) {
        String petImageUrl = s3ImageService.upload(requestPetDTO.getPetImage());
        PetDTO petDTO = PetDTO.fromRequestPetDTO(petImageUrl,
                requestPetDTO);
        System.out.println(petDTO.toString());
        petMapper.updatePet(petDTO);
    }

    public List<ResponsePetDTO> findAllPetsById(Long customerId){
        return petMapper.findAllPetsById(customerId);
    }

    public ResponsePetDetailsDTO findPetById(Long id){
        return petMapper.findPetById(id);
    }
}

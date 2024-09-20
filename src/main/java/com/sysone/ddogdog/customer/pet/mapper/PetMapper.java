package com.sysone.ddogdog.customer.pet.mapper;

import com.sysone.ddogdog.customer.pet.model.PetDTO;
import com.sysone.ddogdog.customer.pet.model.PetSpeciesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    List<PetSpeciesDTO> findAll();
    List<PetSpeciesDTO> findSpeciesById(String query);
    void saveSpecies(PetSpeciesDTO petSpeciesDTO);
    void savePet(PetDTO petDTO);
    List<PetDTO> findAllPetsById(Long userId);
    PetDTO findPetById(Long petId);
}

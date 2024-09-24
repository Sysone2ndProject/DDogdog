package com.sysone.ddogdog.customer.pet.mapper;

import com.sysone.ddogdog.customer.pet.model.PetDTO;
import com.sysone.ddogdog.customer.pet.model.PetSpeciesDTO;
import com.sysone.ddogdog.customer.pet.model.ResponsePetDTO;
import com.sysone.ddogdog.customer.pet.model.ResponsePetDetailsDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetMapper {
    List<PetSpeciesDTO> findAll();
    List<PetSpeciesDTO> findSpeciesById(String query);
    void saveSpecies(PetSpeciesDTO petSpeciesDTO);
    void savePet(PetDTO petDTO);
    void updatePet(PetDTO petDTO);
    List<ResponsePetDTO> findAllPetsById(@Param("customerId")Long customerId);
    ResponsePetDetailsDTO findPetById(@Param("id") Long id);
}

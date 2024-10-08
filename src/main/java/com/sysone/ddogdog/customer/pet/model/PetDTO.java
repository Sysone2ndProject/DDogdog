package com.sysone.ddogdog.customer.pet.model;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class PetDTO {
    private Long id;
    private Long customerId;
    private Long speciesId;
    private String name;
    private Integer age;
    private String gender;
    private String info;
    private String petImage;
    private Integer weight;

    public static PetDTO fromRequestPetDTO(Long customerId,String petImageUrl,RequestPetDTO requestPetDTO){
        return PetDTO.builder()
            .customerId(customerId)
            .speciesId(requestPetDTO.getSpeciesId())
            .name(requestPetDTO.getName())
            .age(requestPetDTO.getAge())
            .gender(requestPetDTO.getGender())
            .info(requestPetDTO.getInfo())
            .petImage(petImageUrl)
            .weight(requestPetDTO.getWeight())
            .build();
    }

    public static PetDTO fromRequestPetDTO(String petImageUrl,RequestPetDTO requestPetDTO){
        return PetDTO.builder().
                id(requestPetDTO.getId())
                .speciesId(requestPetDTO.getSpeciesId())
                .name(requestPetDTO.getName())
                .age(requestPetDTO.getAge())
                .gender(requestPetDTO.getGender())
                .info(requestPetDTO.getInfo())
                .petImage(petImageUrl)
                .weight(requestPetDTO.getWeight())
                .build();
    }
}

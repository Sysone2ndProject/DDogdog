package com.sysone.ddogdog.customer.pet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ResponsePetDetailsDTO {
    private Long id;
    private Long customerId;
    private Long speciesId;
    private String name;
    private Integer age;
    private String gender;
    private String info;
    private String petImage;
    private Integer weight;
    private String species;
}

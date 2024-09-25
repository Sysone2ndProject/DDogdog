package com.sysone.ddogdog.customer.pet.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetSpeciesDTO {
    Long id;
    String species;
}

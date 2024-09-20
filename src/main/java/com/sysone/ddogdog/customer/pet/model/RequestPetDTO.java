package com.sysone.ddogdog.customer.pet.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class RequestPetDTO {
    private Long speciesId;
    private String name;
    private Integer age;
    private String gender;
    private String info;
    private MultipartFile petImage;
    private Integer weight;
}

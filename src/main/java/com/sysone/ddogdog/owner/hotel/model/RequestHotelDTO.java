package com.sysone.ddogdog.owner.hotel.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class RequestHotelDTO {

    private Integer id;
    private Integer addressId;
    private String ownerId;
    private String businessName;
    private String businessNumber;
    private String phoneNumber;
    private String intro;
    private MultipartFile mainImage;
    private String fullAddress;
    private String sido;
    private String sigungu;
    private String dong;
}
package com.sysone.ddogdog.common.address.model;

import com.sysone.ddogdog.owner.hotel.model.RequestHotelDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Integer id;
    private String fullAddress;
    private String sido;
    private String sigungu;
    private String dong;

    public static AddressDTO fromOwnerHotelDTO(RequestHotelDTO requestHotelDTO) {
        return AddressDTO.builder()
            .fullAddress(requestHotelDTO.getFullAddress())
            .sido(requestHotelDTO.getSido())
            .sigungu(requestHotelDTO.getSigungu())
            .dong(requestHotelDTO.getDong())
            .build();
    }

    public static AddressDTO updateAddressDTO(RequestHotelDTO requestHotelDTO) {
        return AddressDTO.builder()
            .id(requestHotelDTO.getAddressId())
            .fullAddress(requestHotelDTO.getFullAddress())
            .sido(requestHotelDTO.getSido())
            .sigungu(requestHotelDTO.getSigungu())
            .dong(requestHotelDTO.getDong())
            .build();
    }
}

package com.sysone.ddogdog.owner.hotel.service;

import com.sysone.ddogdog.common.address.model.AddressDTO;
import com.sysone.ddogdog.common.address.repository.AddressMapper;
import com.sysone.ddogdog.common.config.s3.service.S3ImageService;
import com.sysone.ddogdog.owner.hotel.model.HotelDTO;
import com.sysone.ddogdog.owner.hotel.model.RequestHotelDTO;
import com.sysone.ddogdog.owner.hotel.repository.OwnerHotelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OwnerHotelService {

    private final AddressMapper addressMapper;
    private final OwnerHotelMapper ownerHotelMapper;
    private final S3ImageService s3ImageService;

    @Transactional
    public void saveHotel(String ownerId, RequestHotelDTO requestHotelDTO) {
        requestHotelDTO.setOwnerId(ownerId); //세션에서 꺼내온 값 넣어주기 위해 사용
        AddressDTO addressDTO = AddressDTO.fromOwnerHotelDTO(requestHotelDTO);
        addressMapper.saveAddress(addressDTO);
        Integer addressId = addressDTO.getId();
        String mainImgUrl = s3ImageService.upload(requestHotelDTO.getMainImage());
        HotelDTO hotelDTO = HotelDTO.fromHotelDTO(requestHotelDTO, addressId, mainImgUrl);
        ownerHotelMapper.saveHotel(hotelDTO);
    }

}
package com.sysone.ddogdog.owner.hotel.service;

import com.sysone.ddogdog.common.address.model.AddressDTO;
import com.sysone.ddogdog.common.address.repository.AddressMapper;
import com.sysone.ddogdog.common.config.s3.service.S3ImageService;
import com.sysone.ddogdog.owner.hotel.model.HotelDTO;
import com.sysone.ddogdog.owner.hotel.model.RequestHotelDTO;
import com.sysone.ddogdog.owner.hotel.mapper.OwnerHotelMapper;
import com.sysone.ddogdog.owner.hotel.model.ResponseHotelDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
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
        HotelDTO hotelDTO = HotelDTO.fromRequestHotelDTO(requestHotelDTO, addressId, mainImgUrl);
        ownerHotelMapper.saveHotel(hotelDTO);
    }

    public List<ResponseHotelDTO> getHotelsByUserId(String ownerId) {
        return ownerHotelMapper.getHotelsByUserId(ownerId);
    }

    public ResponseHotelDTO getHotel(Integer id) {
        HotelDTO hotelDTO = ownerHotelMapper.getHotelByID(id);
        String fullAddress = addressMapper.getFullAddressById(hotelDTO.getAddressId());
        return ResponseHotelDTO.fromHotelDTO(hotelDTO, fullAddress);
    }

    @Transactional
    public void updateHotel(RequestHotelDTO requestHotelDTO) {

        AddressDTO addressDTO = AddressDTO.updateAddressDTO(requestHotelDTO);
        addressMapper.updateAddress(addressDTO);

        if (requestHotelDTO.getMainImage() != null) {
            //이미지 파일 존재할 경우에만 S3 업로드
            String mainImgUrl = s3ImageService.upload(requestHotelDTO.getMainImage());
            HotelDTO hotelDTO = HotelDTO.updateWithImg(requestHotelDTO, mainImgUrl);
            ownerHotelMapper.updateHotel(hotelDTO);
        } else {
            HotelDTO hotelDTO = HotelDTO.updateWithOutImg(requestHotelDTO);
            ownerHotelMapper.updateHotelWithOutImg(hotelDTO);
        }
    }
}
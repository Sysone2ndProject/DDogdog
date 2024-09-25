package com.sysone.ddogdog.owner.hotel.mapper;

import com.sysone.ddogdog.owner.hotel.model.HotelDTO;
import com.sysone.ddogdog.owner.hotel.model.ResponseHotelDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerHotelMapper {

    void saveHotel(HotelDTO hotelDTO);

    String getHotelNameByID(Integer id);

    List<ResponseHotelDTO> getHotelsByUserId(String ownerId);

    void updateHotelWithOutImg(HotelDTO hotelDTO);

    HotelDTO getHotelByID(Integer id);

    void updateHotel(HotelDTO hotelDTO);

}
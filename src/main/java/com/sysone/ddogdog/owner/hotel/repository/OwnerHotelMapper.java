package com.sysone.ddogdog.owner.hotel.repository;

import com.sysone.ddogdog.owner.hotel.model.HotelDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerHotelMapper {

    void saveHotel(HotelDTO hotelDTO);
}
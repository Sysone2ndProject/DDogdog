package com.sysone.ddogdog.customer.hotel.mapper;

import com.sysone.ddogdog.customer.hotel.model.Hotel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HotelMapper {

    List<Hotel> getBestHotels();

    List<Hotel> getBestLocalHotels(Integer addressId);

    List<Hotel> getHotelsByKeyword(String keyword);
}

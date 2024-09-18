package com.sysone.ddogdog.customer.hotel.mapper;

import com.sysone.ddogdog.customer.hotel.model.Hotel;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HotelMapper {

    List<Hotel> getBestHotels();

    List<Hotel> getBestLocalHotels(Long customerId);

    List<Integer> getHotelIdByKeywordAndDate(@Param("keyword") String keyword,
        @Param("date") String date);

    List<Hotel> getHotelsByIds(List<Integer> hotelIds);
}

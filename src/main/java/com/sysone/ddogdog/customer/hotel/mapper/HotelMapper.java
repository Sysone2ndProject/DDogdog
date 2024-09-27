package com.sysone.ddogdog.customer.hotel.mapper;

import com.sysone.ddogdog.customer.hotel.model.HotelDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HotelMapper {

    List<HotelDTO> getBestHotels();

    List<HotelDTO> getBestLocalHotels(Long customerId);

    List<Integer> getHotelIdByKeywordAndDate(String date);

    List<HotelDTO> getHotelsByIds(@Param("keyword") String keyword,
        @Param("hotelIds") List<Integer> hotelIds);

    HotelDTO getHotelById(Integer id);

    List<String> findHotelByKeyword(String searchKeyword);
}

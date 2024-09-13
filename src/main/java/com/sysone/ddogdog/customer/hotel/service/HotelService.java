package com.sysone.ddogdog.customer.hotel.service;

import com.sysone.ddogdog.customer.hotel.mapper.HotelMapper;
import com.sysone.ddogdog.customer.hotel.model.Hotel;
import com.sysone.ddogdog.customer.hotel.model.HotelVO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;

    public List<HotelVO> getBestHotels() {
        List<HotelVO> bestHotels = hotelMapper.getBestHotels().stream()
            .map(hotel -> HotelVO.fromHotelDTO(hotel))
            .collect(Collectors.toList());
        return bestHotels;
    }

    public List<Hotel> getBestHotelsById(Integer customerId) {
        return hotelMapper.getBestLocalHotels(customerId);
    }

    public List<HotelVO> getHotelsByKeyword(String keyword) {
        List<HotelVO> listHotels = hotelMapper.getHotelsByKeyword(keyword).stream()
            .map(hotel -> HotelVO.fromHotelDTO(hotel))
            .collect(Collectors.toList());
        return listHotels;
    }
}

package com.sysone.ddogdog.customer.hotel.service;

import com.sysone.ddogdog.customer.hotel.mapper.HotelMapper;
import com.sysone.ddogdog.customer.hotel.model.HotelVO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;

    public List<HotelVO> getBestHotels() {
        return hotelMapper.getBestHotels().stream()
            .map(HotelVO::fromHotelDTO)
            .collect(Collectors.toList());
    }

    public List<HotelVO> getBestHotelsById(Long customerId) {
        return hotelMapper.getBestLocalHotels(customerId).stream()
            .map((HotelVO::fromHotelDTO))
            .collect(Collectors.toList());
    }

    public List<HotelVO> getHotelsByKeywordAndDates(String keyword, String startDate,
        String endDate) {

        List<Integer> hotelIds = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (LocalDate currentDate = start; !currentDate.isAfter(end);
            currentDate = addDays(currentDate)) {
            String date = currentDate.format(formatter);

            List<Integer> hotelIdsForDate = hotelMapper.getHotelIdByKeywordAndDate(date);

            if (hotelIds == null) {
                // 처음 조회한 호텔 ID 리스트
                hotelIds = new ArrayList<>(hotelIdsForDate);
            } else {
                // 교집합을 구함
                hotelIds.retainAll(hotelIdsForDate);
            }

            // 교집합이 비어지면 더 이상 조회할 필요 없음
            if (hotelIds.isEmpty()) {
                break;
            }
        }

        if (hotelIds != null && !hotelIds.isEmpty()) {
            return getHotelByIds(keyword, hotelIds);
        }

        return Collections.emptyList();
    }

    private List<HotelVO> getHotelByIds(String keyword, List<Integer> hotelIds) {
        return hotelMapper.getHotelsByIds(keyword, hotelIds).stream()
            .map(HotelVO::fromHotelDTO)
            .collect(Collectors.toList());
    }

    private LocalDate addDays(LocalDate date) {
        return date.plusDays(1);
    }

}

package com.sysone.ddogdog.customer.hotel.service;

import com.sysone.ddogdog.customer.hotel.mapper.HotelMapper;
import com.sysone.ddogdog.customer.hotel.model.HotelVO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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

    public List<HotelVO> getHotelsByKeywordAndDates(String keyword, Date startDate, Date endDate) {

        List<Integer> hotelIds = null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        for (Date currentDate = startDate; !currentDate.after(endDate);
            currentDate = addDays(currentDate)) {
            String date = sdf.format(currentDate);

            List<Integer> hotelIdsForDate = hotelMapper.getHotelIdByKeywordAndDate(keyword, date);

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
            return getHotelByIds(hotelIds);
        }

        return Collections.emptyList();
    }

    private List<HotelVO> getHotelByIds(List<Integer> hotelIds) {
        return hotelMapper.getHotelsByIds(hotelIds).stream()
            .map(HotelVO::fromHotelDTO)
            .collect(Collectors.toList());
    }

    private Date addDays(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

}

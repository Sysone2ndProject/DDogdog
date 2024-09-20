package com.sysone.ddogdog.customer.hotel.service;

import com.sysone.ddogdog.customer.hotel.mapper.HotelMapper;
import com.sysone.ddogdog.customer.hotel.model.HotelDTO;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDetailsDTO;
import com.sysone.ddogdog.customer.room.mapper.RoomMapper;
import com.sysone.ddogdog.customer.room.model.ResponseRoomDTO;
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
    private final RoomMapper roomMapper;

    public List<ResponseHotelDTO> getBestHotels() {
        return hotelMapper.getBestHotels().stream()
            .map(ResponseHotelDTO::fromHotelDTO)
            .collect(Collectors.toList());
    }

    public List<ResponseHotelDTO> getBestHotelsById(Long customerId) {
        return hotelMapper.getBestLocalHotels(customerId).stream()
            .map((ResponseHotelDTO::fromHotelDTO))
            .collect(Collectors.toList());
    }

    public List<ResponseHotelDTO> getHotelsByKeywordAndDates(String keyword, String startDate,
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

    private List<ResponseHotelDTO> getHotelByIds(String keyword, List<Integer> hotelIds) {
        return hotelMapper.getHotelsByIds(keyword, hotelIds).stream()
            .map(ResponseHotelDTO::fromHotelDTO)
            .collect(Collectors.toList());
    }

    private LocalDate addDays(LocalDate date) {
        return date.plusDays(1);
    }

    public ResponseHotelDetailsDTO getHotelDetails(Integer id, String startDate, String endDate) {
        HotelDTO hotelDTO = hotelMapper.getHotelById(id);
        ResponseHotelDTO hotel = ResponseHotelDTO.fromHotelDTO(hotelDTO);

        List<ResponseRoomDTO> rooms = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate start = (startDate == null) ? LocalDate.now()
            : LocalDate.parse(startDate, formatter);
        LocalDate end = (startDate == null && endDate == null) ? start.plusDays(1)
            : LocalDate.parse(endDate, formatter);

        for (LocalDate currentDate = start; !currentDate.isAfter(end);
            currentDate = addDays(currentDate)) {
            String date = currentDate.format(formatter);

            List<ResponseRoomDTO> roomsForDate = roomMapper.getRoomsByHotelId(id, date);

            if (rooms == null) {
                // 처음 조회한 호텔 ID 리스트
                rooms = roomsForDate;
            } else {
                for (int i = 0; i < roomsForDate.size(); i++) {
                    if (roomsForDate.get(i).getCount() < rooms.get(i).getCount()) {
                        rooms.set(i, roomsForDate.get(i));
                    }
                }
            }
        }

        return new ResponseHotelDetailsDTO(hotel, rooms);
    }
}

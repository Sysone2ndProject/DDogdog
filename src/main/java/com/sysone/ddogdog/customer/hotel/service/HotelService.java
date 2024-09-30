package com.sysone.ddogdog.customer.hotel.service;

import com.sysone.ddogdog.common.address.model.AddressDTO;
import com.sysone.ddogdog.common.address.repository.AddressMapper;
import com.sysone.ddogdog.customer.hotel.mapper.HotelMapper;
import com.sysone.ddogdog.customer.hotel.model.HotelDTO;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDTO;
import com.sysone.ddogdog.customer.hotel.model.ResponseHotelDetailsDTO;
import com.sysone.ddogdog.customer.room.mapper.RoomMapper;
import com.sysone.ddogdog.customer.room.model.ResponseRoomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelMapper hotelMapper;
    private final RoomMapper roomMapper;
    private final AddressMapper addressMapper;

    public List<ResponseHotelDTO> getBestHotels() {
        return hotelMapper.getBestHotels().stream()
            .map(h -> ResponseHotelDTO.fromHotelDTO(h,
                addressMapper.getFullAddressById(h.getAddressId())))
            .collect(Collectors.toList());
    }

    public List<ResponseHotelDTO> getBestHotelsById(Long customerId) {
        return hotelMapper.getBestLocalHotels(customerId).stream()
            .map(h -> ResponseHotelDTO.fromHotelDTO(h,
                addressMapper.getFullAddressById(h.getAddressId())))
            .collect(Collectors.toList());
    }

    public Page<ResponseHotelDTO> getHotelsByKeywordAndDatesWithPagination(String keyword,
        String startDate,
        String endDate, int page, int size) {

        List<Integer> hotelIds = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);

        for (LocalDate currentDate = start; !currentDate.isAfter(end);
            currentDate = addDays(currentDate)) {
            String date = currentDate.format(formatter);
            List<Integer> hotelIdsForDate = hotelMapper.getHotelIdByKeywordAndDate(date);

            if (hotelIds == null) {
                hotelIds = new ArrayList<>(hotelIdsForDate);
            } else {
                hotelIds.retainAll(hotelIdsForDate);
            }

            if (hotelIds.isEmpty()) {
                break;
            }
        }

        if (hotelIds != null && !hotelIds.isEmpty()) {
            List<ResponseHotelDTO> allHotels = getHotelByIds(keyword, hotelIds); // 모든 호텔 정보 조회

            // 페이징 처리
            int totalCount = allHotels.size(); // 전체 호텔 개수
            int startRow = Math.min((page - 1) * size, totalCount); // 시작 인덱스
            int endRow = Math.min(startRow + size, totalCount); // 끝 인덱스

            // 페이징된 호텔 리스트
            List<ResponseHotelDTO> pagedHotels = allHotels.subList(startRow, endRow);

            // Page 객체 생성
            Pageable pageable = PageRequest.of(page - 1, size); // 0 기반 인덱스
            return new PageImpl<>(pagedHotels, pageable, totalCount); // Page 반환
        }

        return new PageImpl<>(Collections.emptyList(), PageRequest.of(page - 1, size),
            0); // 데이터가 없을 경우
    }

    private List<ResponseHotelDTO> getHotelByIds(String keyword, List<Integer> hotelIds) {
        return hotelMapper.getHotelsByIds(keyword, hotelIds).stream()
            .map(h -> ResponseHotelDTO.fromHotelDTO(h,
                addressMapper.getFullAddressById(h.getAddressId())))
            .collect(Collectors.toList());
    }

    private LocalDate addDays(LocalDate date) {
        return date.plusDays(1);
    }

    public ResponseHotelDetailsDTO getHotelDetails(Integer id, String startDate, String endDate) {
        HotelDTO hotelDTO = hotelMapper.getHotelById(id);
        AddressDTO address = addressMapper.getAddressById(hotelDTO.getAddressId());
        ResponseHotelDTO hotel = ResponseHotelDTO.fromHotelDTO(hotelDTO,
            addressMapper.getFullAddressById(hotelDTO.getAddressId()));

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
                rooms = roomsForDate;
            } else {
                for (int i = 0; i < roomsForDate.size(); i++) {
                    if (roomsForDate.get(i).getCount() < rooms.get(i).getCount()) {
                        rooms.set(i, roomsForDate.get(i));
                    }
                }
            }
        }

        return new ResponseHotelDetailsDTO(address, hotel, rooms);
    }

    public List<String> getDataList(String searchKeyword) {
        return hotelMapper.findHotelByKeyword(searchKeyword);
    }
}

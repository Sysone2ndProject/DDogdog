//package com.sysone.ddogdog.customer.roomChoice;
//
//import com.sysone.ddogdog.customer.hotel.model.HotelVO;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class RoomChoiceService {
//    public List<HotelVO> getRoomsByTypesAndDates(String types, String startDate,
//        String endDate) {
//
//        List<Integer> roomIds = null;
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//        LocalDate start = LocalDate.parse(startDate, formatter);
//        LocalDate end = LocalDate.parse(endDate, formatter);
//
//        // 해당 리스트에서의 타입을 반복문으로 받아서 HashMap에 넣는다
//        // 해당 방 개수 조회해서 value 늘리기
//        HashSet<>
//
//        // 해당 값들 반환하며 반복문을 돌린다
//        //그러면서 일자별로 조회
//        // 교집합 구한뒤
//        // 제일 낮은 pk 뽑아낸다
//        //
//        for(int i=0;i<types.)
//        for (LocalDate currentDate = start; !currentDate.isAfter(end);
//            currentDate = addDays(currentDate)) {
//            String date = currentDate.format(formatter);
//
//            List<Integer> hotelIdsForDate = hotelMapper.getHotelIdByKeywordAndDate(date);
//
//            if (hotelIds == null) {
//                // 처음 조회한 호텔 ID 리스트
//                hotelIds = new ArrayList<>(hotelIdsForDate);
//            } else {
//                // 교집합을 구함
//                hotelIds.retainAll(hotelIdsForDate);
//            }
//
//            // 교집합이 비어지면 더 이상 조회할 필요 없음
//            if (hotelIds.isEmpty()) {
//                break;
//            }
//        }
//
//        if (hotelIds != null && !hotelIds.isEmpty()) {
//            return getHotelByIds(keyword, hotelIds);
//        }
//
//        return Collections.emptyList();
//    }
//
//    private List<HotelVO> getHotelByIds(String keyword, List<Integer> hotelIds) {
//        return hotelMapper.getHotelsByIds(keyword, hotelIds).stream()
//            .map(HotelVO::fromHotelDTO)
//            .collect(Collectors.toList());
//    }
//
//    private LocalDate addDays(LocalDate date) {
//        return date.plusDays(1);
//    }
//}

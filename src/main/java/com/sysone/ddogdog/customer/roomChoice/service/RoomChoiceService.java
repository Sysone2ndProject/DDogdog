package com.sysone.ddogdog.customer.roomChoice.service;

import com.sysone.ddogdog.customer.roomChoice.exception.NoAvailableRoomsException;
import com.sysone.ddogdog.customer.roomChoice.mapper.RoomChoiceMapper;
import com.sysone.ddogdog.customer.roomChoice.model.RequestRoomChoiceDTO;
import com.sysone.ddogdog.customer.roomChoice.model.RoomChoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomChoiceService {

    private final RoomChoiceMapper roomChoiceMapper;

    @Transactional
    public void saveRoomChoice(Integer reservationId, LocalDate start, LocalDate end, Integer hotelId, List<RequestRoomChoiceDTO> rooms) {

        //TODO : 전체 RoomType 프론트에서 입력받을 경우 rooms.getCount = 0인지 판별 로직 세우기
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 1; i < rooms.size(); i++) {
            List<Integer> roomIds = null;
            RequestRoomChoiceDTO room = rooms.get(i);

            for (LocalDate currentDate = start; !currentDate.isAfter(end); currentDate = addDays(currentDate)) {
                String date = currentDate.format(formatter);

                List<Integer> roomIdsForTypeAndDate = roomChoiceMapper.getRoomIdsByTypeAndDate(room.getRoomGrade(), date, hotelId);
                if (roomIds == null) {
                    roomIds = new ArrayList<>(roomIdsForTypeAndDate);
                } else {
                    // 교집합을 구함
                    roomIds.retainAll(roomIdsForTypeAndDate);
                }

                // 교집합이 없음 -> 방이없음 -> 예외
                if (roomIds.isEmpty()) {
                    throw new NoAvailableRoomsException("선택한 날짜와 조건에 맞는 방이 없습니다.");
                }

                Collections.sort(roomIds);
            }
            // 객실 조회 테이블에 insert
            for (int count = 0; count < room.getCount(); count++) {
                RoomChoice roomChoice = RoomChoice.from(reservationId, roomIds.get(count), room.getPrice());
                roomChoiceMapper.saveChooseRooms(roomChoice);
            }
        }
    }

    private LocalDate addDays(LocalDate date) {
        return date.plusDays(1);
    }
}

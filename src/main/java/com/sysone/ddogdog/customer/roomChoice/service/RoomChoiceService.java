package com.sysone.ddogdog.customer.roomChoice.service;

import com.sysone.ddogdog.common.exception.CustomerErrorCode;
import com.sysone.ddogdog.common.exception.NoAvailableRoomsException;
import com.sysone.ddogdog.customer.roomChoice.mapper.RoomChoiceMapper;
import com.sysone.ddogdog.customer.roomChoice.model.RequestRoomChoiceDTO;
import com.sysone.ddogdog.customer.roomChoice.model.ResponseRoomChoiceDTO;
import com.sysone.ddogdog.customer.roomChoice.model.RoomChoice;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomChoiceService {

    private final RoomChoiceMapper roomChoiceMapper;

    /**
     * 생성된 예약번호를 받아 해당 기간, 타입에 해당하는 빈 방을 탐색하여 객실 생성
     * @param reservationId 예약 Id
     * @param start 시작일
     * @param end 종료일
     * @param hotelId 호텔 Id
     * @param rooms 생성하는 room 정보들
     */
    @Transactional
    public void saveRoomChoice(Integer reservationId, LocalDate start, LocalDate end,
        Integer hotelId, List<RequestRoomChoiceDTO> rooms) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < rooms.size(); i++) {
            List<Integer> roomIds = null;
            RequestRoomChoiceDTO room = rooms.get(i);
            if(room.getCount()==0){
                continue;
            }

            for (LocalDate currentDate = start; !currentDate.isAfter(end);
                currentDate = addDays(currentDate)) {
                String date = currentDate.format(formatter);
                List<Integer> roomIdsForTypeAndDate = roomChoiceMapper.getRoomIdsByTypeAndDate(
                    room.getGrade(), date, hotelId);
                if (roomIds == null) {
                    roomIds = new ArrayList<>(roomIdsForTypeAndDate);
                } else {
                    // 교집합을 구함
                    roomIds.retainAll(roomIdsForTypeAndDate);
                }

                // 교집합이 없음 -> 방이없음 -> 예외
                if (roomIds.isEmpty()) {
                    throw new NoAvailableRoomsException(CustomerErrorCode.NO_MORE_ROOMS);
                }

                Collections.sort(roomIds);
            }
            // 객실 조회 테이블에 insert
            for (int count = 0; count < room.getCount(); count++) {
                RoomChoice roomChoice = RoomChoice.from(reservationId, roomIds.get(count),
                    room.getPrice());
                roomChoiceMapper.saveChooseRooms(roomChoice);
            }
        }
    }

    private LocalDate addDays(LocalDate date) {
        return date.plusDays(1);
    }

    /**
     * 해당 예약 내역에 속한 객실 리스트를 반환하는 메서드
     * @param customerId
     * @return List<ResponseRoomChoiceDTO> 객실정보들
     */
    public List<ResponseRoomChoiceDTO> findAllRooms(Integer customerId) {
        return roomChoiceMapper.findAllRooms(customerId);
    }
}

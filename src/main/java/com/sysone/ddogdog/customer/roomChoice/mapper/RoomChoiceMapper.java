package com.sysone.ddogdog.customer.roomChoice.mapper;

import com.sysone.ddogdog.customer.room.model.RoomGrade;
import com.sysone.ddogdog.customer.roomChoice.model.RoomChoice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoomChoiceMapper {
    List<Integer> getRoomIdsByTypeAndDate(@Param("roomGrade") RoomGrade roomGrade, @Param("date") String date, @Param("hotelId") Integer hotelId);

    void saveChooseRooms(RoomChoice roomChoice);
}

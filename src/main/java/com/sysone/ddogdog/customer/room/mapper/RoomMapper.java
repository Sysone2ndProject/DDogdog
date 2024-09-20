package com.sysone.ddogdog.customer.room.mapper;

import com.sysone.ddogdog.customer.room.model.ResponseRoomDTO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoomMapper {

    List<ResponseRoomDTO> getRoomsByHotelId(@Param("id") Integer id, @Param("date") String date);
}

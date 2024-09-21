package com.sysone.ddogdog.owner.room.mapper;

import com.sysone.ddogdog.owner.room.model.RoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomGrade;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerRoomMapper {

    void saveRoom(RoomDTO roomDTO);

    List<RoomDTO> getRoomsByHotelId(Integer hotelId);

    RoomDTO getHotelByIDAndGrade(Integer hotelId, RoomGrade grade);

    void updateRoom(RoomDTO roomDTO);

    void updateRoomWithoutImg(RoomDTO roomDTO);
}
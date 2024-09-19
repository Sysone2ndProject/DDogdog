package com.sysone.ddogdog.owner.room.mapper;

import com.sysone.ddogdog.owner.room.model.RoomDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper {
    void saveRoom(RoomDTO roomDTO);
}
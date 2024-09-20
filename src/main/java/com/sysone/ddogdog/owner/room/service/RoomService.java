package com.sysone.ddogdog.owner.room.service;

import com.sysone.ddogdog.common.config.s3.service.S3ImageService;
import com.sysone.ddogdog.owner.hotel.mapper.OwnerHotelMapper;
import com.sysone.ddogdog.owner.room.mapper.RoomMapper;
import com.sysone.ddogdog.owner.room.model.RequestRoomDTO;
import com.sysone.ddogdog.owner.room.model.ResponseRoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService {

    private final S3ImageService s3ImageService;
    private final RoomMapper roomMapper;
    private final OwnerHotelMapper hotelMapper;

    @Transactional
    public void saveRoom(RequestRoomDTO requestRoomDTO) {
        String roomImgUrl = s3ImageService.upload(requestRoomDTO.getRoomImage());
        RoomDTO roomDTO = RoomDTO.fromRequestRoomDTO(requestRoomDTO, roomImgUrl);
        log.info(roomDTO.toString());
        for (Integer i = 0; i < requestRoomDTO.getRoomCount(); i++) {
            roomMapper.saveRoom(roomDTO);
        }
    }

    public RoomVO getRoomList(Integer hotelId) {
        String businessName = hotelMapper.getHotelNameByID(hotelId);
        List<ResponseRoomDTO> responseRoomDTOList = roomMapper.getRoomsByHotelId(hotelId);
        return new RoomVO(hotelId,businessName,responseRoomDTOList);
    }
}

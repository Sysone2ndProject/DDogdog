package com.sysone.ddogdog.owner.room.service;

import com.sysone.ddogdog.common.config.s3.service.S3ImageService;
import com.sysone.ddogdog.owner.hotel.mapper.OwnerHotelMapper;
import com.sysone.ddogdog.owner.room.mapper.OwnerRoomMapper;
import com.sysone.ddogdog.owner.room.model.RequestRoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomGrade;
import com.sysone.ddogdog.owner.room.model.ResponseRoomDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoomService {

    private final S3ImageService s3ImageService;
    private final OwnerRoomMapper ownerRoomMapper;
    private final OwnerHotelMapper hotelMapper;

    @Transactional
    public void saveRoom(RequestRoomDTO requestRoomDTO) {
        String roomImgUrl = s3ImageService.upload(requestRoomDTO.getRoomImage());
        RoomDTO roomDTO = RoomDTO.fromRequestRoomDTO(requestRoomDTO, roomImgUrl);
        log.info(roomDTO.toString());
        for (Integer i = 0; i < requestRoomDTO.getRoomCount(); i++) {
            ownerRoomMapper.saveRoom(roomDTO);
        }
    }

    public ResponseRoomDTO getRoomList(Integer hotelId) {
        String businessName = hotelMapper.getHotelNameByID(hotelId);
        List<RoomDTO> responseRoomDTOList = ownerRoomMapper.getRoomsByHotelId(hotelId);
        return new ResponseRoomDTO(hotelId, businessName, responseRoomDTOList);
    }

    public RoomDTO getRoom(RoomGrade grade, Integer hotelId) {
        return ownerRoomMapper.getHotelByIDAndGrade(hotelId, grade);
    }

    @Transactional
    public void updateRoom(RequestRoomDTO requestRoomDTO) {
        if (requestRoomDTO.getRoomImage() != null) {
            String ImgUrl = s3ImageService.upload(requestRoomDTO.getRoomImage());
            RoomDTO roomDTO = RoomDTO.upDateRoom(requestRoomDTO, ImgUrl);
            ownerRoomMapper.updateRoom(roomDTO);

        } else {
            RoomDTO roomDTO = RoomDTO.updateRoomWithoutImg(requestRoomDTO);
            ownerRoomMapper.updateRoomWithoutImg(roomDTO);
        }
    }

    public List<RoomGrade> getHotelExistGrade(Integer hotelId){
        //전체 등급 리스트
        List<RoomGrade> allGrade = List.of(RoomGrade.values());
        //호텔 보유 등급 리스트
        List<RoomGrade> existGrades = ownerRoomMapper.getHotelExistGrade(hotelId);

        // 호텔 등록된 객실 존재 시 해당 등급 제외 후 반환, 없을 시 전체 반환
        return allGrade.stream()
            .filter(grade -> !existGrades.contains(grade))
            .collect(Collectors.toList());
    }
}

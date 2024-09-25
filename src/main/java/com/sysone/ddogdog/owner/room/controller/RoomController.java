package com.sysone.ddogdog.owner.room.controller;

import com.sysone.ddogdog.owner.room.model.RequestRoomDTO;
import com.sysone.ddogdog.owner.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("ownerRoomController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/rooms")
public class RoomController {

    private final RoomService roomService;


    @PostMapping
    public ResponseEntity<Void> saveRoom(@ModelAttribute RequestRoomDTO requestRoomDTO) {
        roomService.saveRoom(requestRoomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping
    public ResponseEntity<Void> getUpdateRoom(@ModelAttribute RequestRoomDTO requestRoomDTO) {
        roomService.updateRoom(requestRoomDTO);
        return ResponseEntity.ok().build();
    }
}
package com.sysone.ddogdog.owner.room.controller;

import com.sysone.ddogdog.owner.room.model.RequestRoomDTO;
import com.sysone.ddogdog.owner.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("ownerRoomController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{hotelId}")
    public String getRoomRegisterForm(@PathVariable Integer hotelId) {
        return "owner/roomRegister";
    }

    @PostMapping
    public ResponseEntity<Void> saveRoom(@ModelAttribute RequestRoomDTO requestRoomDTO) {
        roomService.saveRoom(requestRoomDTO);
        return ResponseEntity.created(null).build();
    }
}
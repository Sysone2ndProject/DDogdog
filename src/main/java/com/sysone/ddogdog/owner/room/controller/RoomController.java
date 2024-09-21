package com.sysone.ddogdog.owner.room.controller;

import com.sysone.ddogdog.owner.room.model.RequestRoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomDTO;
import com.sysone.ddogdog.owner.room.model.RoomGrade;
import com.sysone.ddogdog.owner.room.model.ResponseRoomDTO;
import com.sysone.ddogdog.owner.room.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("ownerRoomController")
@RequiredArgsConstructor
@RequestMapping("/v1/owners/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/form/{hotelId}")
    public String getRoomRegisterForm(@PathVariable Integer hotelId, Model model) {
        List<RoomGrade> grades = roomService.getHotelExistGrade(hotelId);
        model.addAttribute("hotelId", hotelId);
        model.addAttribute("grades",grades);
        return "owner/roomRegister";
    }

    @PostMapping
    public ResponseEntity<Void> saveRoom(@ModelAttribute RequestRoomDTO requestRoomDTO) {
        roomService.saveRoom(requestRoomDTO);
        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public String getRoomList(@RequestParam("hotelId") Integer hotelId, Model model) {
        ResponseRoomDTO rooms = roomService.getRoomList(hotelId);
        model.addAttribute("hotelId", rooms.getHotelId());
        model.addAttribute("hotelName", rooms.getBusiness_name());
        model.addAttribute("rooms", rooms.getResponseRoomDTOList());
        return "owner/roomList";
    }

    @GetMapping("/updateform/{grade}")
    public String getUpdateRoomForm(@PathVariable RoomGrade grade, @RequestParam("hotelId") Integer hotelId, Model model) {
        RoomDTO room = roomService.getRoom(grade, hotelId);
        model.addAttribute("room", room);
        model.addAttribute("hotelId", hotelId);
        return "owner/updateRoom";
    }

    @PutMapping
    public ResponseEntity<Void> getUpdateRoom(@ModelAttribute RequestRoomDTO requestRoomDTO) {
        roomService.updateRoom(requestRoomDTO);
        return ResponseEntity.ok().build();
    }
}
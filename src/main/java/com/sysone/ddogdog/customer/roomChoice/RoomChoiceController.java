package com.sysone.ddogdog.customer.roomChoice;

import com.sysone.ddogdog.customer.roomChoice.model.ResponseRoomChoiceDTO;
import com.sysone.ddogdog.customer.roomChoice.service.RoomChoiceService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers/roomChoice")
@RequiredArgsConstructor
public class RoomChoiceController {

    private final RoomChoiceService roomChoiceService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ResponseRoomChoiceDTO>> findAllRoomChoices(
        @PathVariable Integer id) {
        System.out.println("입력확인" + id);
        List<ResponseRoomChoiceDTO> roomsInfos = roomChoiceService.findAllRooms(id);
        for (ResponseRoomChoiceDTO r : roomsInfos) {
            System.out.println(r.toString());
        }
        return ResponseEntity.ok(roomsInfos);
    }
}

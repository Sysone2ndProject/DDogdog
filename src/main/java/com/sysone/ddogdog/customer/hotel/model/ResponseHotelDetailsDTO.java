package com.sysone.ddogdog.customer.hotel.model;

import com.sysone.ddogdog.customer.room.model.ResponseRoomDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ResponseHotelDetailsDTO {

    private ResponseHotelDTO hotel;
    private List<ResponseRoomDTO> rooms;

}

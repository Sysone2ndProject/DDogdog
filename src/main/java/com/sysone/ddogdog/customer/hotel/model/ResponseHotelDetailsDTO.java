package com.sysone.ddogdog.customer.hotel.model;

import com.sysone.ddogdog.customer.room.model.ResponseRoomDTO;
import java.util.List;

public class ResponseHotelDetailsDTO {

    private ResponseHotelDTO hotel;
    private List<ResponseRoomDTO> rooms;

    public ResponseHotelDetailsDTO() {
    }

    public ResponseHotelDetailsDTO(ResponseHotelDTO hotel, List<ResponseRoomDTO> rooms) {
        this.hotel = hotel;
        this.rooms = rooms;
    }
}

package com.sysone.ddogdog.customer.hotel.model;

import com.sysone.ddogdog.common.address.model.AddressDTO;
import com.sysone.ddogdog.customer.room.model.ResponseRoomDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ResponseHotelDetailsDTO {

    private AddressDTO address;
    private ResponseHotelDTO hotel;
    private List<ResponseRoomDTO> rooms;

}

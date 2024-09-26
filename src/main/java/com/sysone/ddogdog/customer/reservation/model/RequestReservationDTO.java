package com.sysone.ddogdog.customer.reservation.model;

import com.sysone.ddogdog.customer.roomChoice.model.RequestRoomChoiceDTO;
import lombok.Data;

import java.util.List;

/**
 * 프론트로부터 정보를 받는 객체, 추후 DB와 통신하기 위해 Entity로 가공
 */

@Data
public class RequestReservationDTO {
    Long customerId;
    Integer hotelId;
    String startDate;
    String endDate;
    Integer count;
    Integer price;
    List<RequestRoomChoiceDTO> rooms;
}

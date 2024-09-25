package com.sysone.ddogdog.owner.room.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomGrade {
    DELUXE("디럭스   | 3평 미만"),
    SUPERIOR("슈페리어  | 3평이상 5평미만"),
    SUITE("스위트   | 5평이상 7평미만"),
    ROYAL_SUITE("로얄스위트 | 7평이상");

    private final String detail;

}
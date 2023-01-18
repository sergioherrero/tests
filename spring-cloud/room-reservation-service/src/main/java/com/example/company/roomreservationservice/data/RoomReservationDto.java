package com.example.company.roomreservationservice.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RoomReservationDto {

    private long guestId;
    private long roomId;
    private long reservationId;
    private String firstName;
    private String lastName;
    private String name;
    private String roomNumber;
    private String bedInfo;
    private String date;
}

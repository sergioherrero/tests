package com.example.company.roomreservationservice.converter;

import com.example.company.roomreservationservice.data.RoomReservation;
import com.example.company.roomreservationservice.data.RoomReservationDto;
import org.springframework.core.convert.converter.Converter;

public class RoomReservationToDto implements Converter<RoomReservation, RoomReservationDto> {

    @Override
    public RoomReservationDto convert(RoomReservation roomReservation) {
        return RoomReservationDto.builder()
                .reservationId(roomReservation.getReservationId())
                .name(roomReservation.getName())
                .roomId(roomReservation.getRoomId())
                .roomNumber(roomReservation.getRoomNumber())
                .date(roomReservation.getDate())
                .guestId(roomReservation.getGuestId())
                .lastName(roomReservation.getLastName())
                .firstName(roomReservation.getFirstName())
                .bedInfo(roomReservation.getBedInfo())
                .build();
    }

}

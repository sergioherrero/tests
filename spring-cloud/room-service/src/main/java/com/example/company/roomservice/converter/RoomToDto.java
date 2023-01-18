package com.example.company.roomservice.converter;

import com.example.company.roomservice.data.Room;
import com.example.company.roomservice.data.RoomDto;
import org.springframework.core.convert.converter.Converter;

public class RoomToDto implements Converter<Room, RoomDto> {

    @Override
    public RoomDto convert(Room room) {
        return RoomDto.builder()
                .roomId(room.getRoomId())
                .roomNumber(room.getRoomNumber())
                .name(room.getName())
                .bedInfo(room.getBedInfo())
                .build();
    }
}

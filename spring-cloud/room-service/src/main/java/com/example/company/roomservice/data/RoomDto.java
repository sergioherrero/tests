package com.example.company.roomservice.data;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class RoomDto {

    private long roomId;

    private String name;

    private String roomNumber;

    private String bedInfo;
}

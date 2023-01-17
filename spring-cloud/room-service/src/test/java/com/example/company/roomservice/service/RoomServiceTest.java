package com.example.company.roomservice.service;

import com.example.company.roomservice.data.Room;
import com.example.company.roomservice.data.RoomDto;
import com.example.company.roomservice.error.NotFoundException;
import com.example.company.roomservice.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    RoomRepository roomRepository;

    @Mock
    ConversionService conversionService;

    @InjectMocks
    RoomService roomService;

    @Test
    public void when_find_all_should_return_room_list_dto() {

        Room room1 = createRoomById(1);
        Room room2 = createRoomById(2);

        when(roomRepository.findAll()).thenReturn(List.of(room1, room2));
        when(conversionService.convert(room1, RoomDto.class)).thenReturn(createRoomDtoById(1));
        when(conversionService.convert(room2, RoomDto.class)).thenReturn(createRoomDtoById(2));
        List<RoomDto> rooms = roomService.findAll();
        assertEquals(2, rooms.size());
    }

    @Test
    public void when_save_should_return_created_room_dto() {

        Room room1 = createRoomById(1);
        RoomDto roomDto1 = createRoomDtoById(1);

        when(roomRepository.save(any(Room.class))).thenReturn(room1);
        when(conversionService.convert(room1, RoomDto.class)).thenReturn(createRoomDtoById(1));
        RoomDto roomDto = roomService.save(roomDto1);
        assertEquals(roomDto.getRoomId(), room1.getRoomId());
        assertEquals(roomDto.getRoomNumber(), room1.getRoomNumber());
        assertEquals(roomDto.getName(), room1.getName());
    }

    @Test
    public void when_update_should_return_nothing() {
        Room room1 = createRoomById(1);
        RoomDto roomDto1 = createRoomDtoById(1);

        when(roomRepository.save(any(Room.class))).thenReturn(room1);
        lenient().when(conversionService.convert(room1, RoomDto.class)).thenReturn(createRoomDtoById(1));
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(room1));
        roomService.save(roomDto1, 1);
    }

    @Test
    public void when_update_should_throw_not_found() {
        Room room1 = createRoomById(1);
        RoomDto roomDto1 = createRoomDtoById(1);

        lenient().when(roomRepository.save(any(Room.class))).thenReturn(room1);
        lenient().when(conversionService.convert(room1, RoomDto.class)).thenReturn(createRoomDtoById(1));
        assertThrows(NotFoundException.class, () -> roomService.save(roomDto1, 2));
    }

    private Room createRoomById(long id) {
        Room r = new Room();
        r.setRoomId(id);
        r.setRoomNumber("roomNumber" +id);
        r.setName("name" +id);
        r.setBedInfo("bedInfo" +id);
        return r;
    }

    private RoomDto createRoomDtoById(long id) {
        return RoomDto.builder()
                .roomId(id)
                .name("name"+id)
                .roomNumber("roomNumber" +id)
                .bedInfo("bedInfo" +id)
                .build();
    }
}

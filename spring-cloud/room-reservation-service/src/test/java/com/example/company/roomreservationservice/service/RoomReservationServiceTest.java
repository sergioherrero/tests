package com.example.company.roomreservationservice.service;

import com.example.company.roomreservationservice.client.reservation.Reservation;
import com.example.company.roomreservationservice.client.reservation.ReservationServiceClient;
import com.example.company.roomreservationservice.client.room.Room;
import com.example.company.roomreservationservice.client.room.RoomServiceClient;
import com.example.company.roomreservationservice.data.RoomReservationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomReservationServiceTest {

    @Mock
    ConversionService conversionService;
    @InjectMocks
    RoomReservationService roomReservationService;

    @Mock
    RefreshScopedService refreshScopedService;

    @Mock
    RoomServiceClient roomServiceClient;

    @Mock
    ReservationServiceClient reservationServiceClient;

    @Test
    public void when_find_all_should_return_room_list_dto() {

        when(roomServiceClient.getAll()).thenReturn(List.of(new Room()));
        lenient().when(reservationServiceClient.getAll(anyString(), anyLong())).thenReturn(List.of(new Reservation()));

        Collection<RoomReservationDto> roomReservationsDto = roomReservationService.getRoomReservations("09/09/1986");
        assertEquals(1, roomReservationsDto.size());
    }
}

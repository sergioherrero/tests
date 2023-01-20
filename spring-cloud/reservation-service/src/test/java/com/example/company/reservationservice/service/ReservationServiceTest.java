package com.example.company.reservationservice.service;

import com.example.company.reservationservice.api.ReservationDto;
import com.example.company.reservationservice.data.Reservation;
import com.example.company.reservationservice.data.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationService reservationService;

    @Test
    public void when_find_all_should_return_reservation_list_dto() {

        reservationService.getAll(1L, "09-09-1986");
        lenient().when(reservationRepository.findReservationEntitiesByDateAndGuestId(any(), eq(1L)))
                .thenReturn(List.of(new Reservation(), new Reservation()));
    }

    @Test
    public void when_save_should_return_created_room_dto() {

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setGuestId(1);
        reservation.setRoomId(1);
        reservation.setDate(new Date(1986, 9, 9));
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(1);
        reservationDto.setRoomId(1);
        reservationDto.setDate("09-09-1986");
        reservationDto.setGuestId(1);

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        ReservationDto reservationDtoResponse = reservationService.createReservation(reservationDto);
        assertEquals(reservationDtoResponse.getRoomId(), reservationDto.getRoomId());
        assertEquals(reservationDtoResponse.getReservationId(), reservationDto.getReservationId());
        assertEquals(reservationDtoResponse.getGuestId(), reservationDto.getGuestId());
    }

    @Test
    public void when_update_should_return_nothing() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(1);
        reservationDto.setRoomId(1);
        reservationDto.setDate("09-09-1986");
        reservationDto.setGuestId(1);
        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setGuestId(1);
        reservation.setRoomId(1);
        reservation.setDate(new Date(1986, 9, 9));

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);
        reservationService.updateReservation(1, reservationDto);
    }
}

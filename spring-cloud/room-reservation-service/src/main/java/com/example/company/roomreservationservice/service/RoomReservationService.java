package com.example.company.roomreservationservice.service;

import com.example.company.roomreservationservice.client.guest.Guest;
import com.example.company.roomreservationservice.client.guest.GuestServiceClient;
import com.example.company.roomreservationservice.client.reservation.Reservation;
import com.example.company.roomreservationservice.client.reservation.ReservationServiceClient;
import com.example.company.roomreservationservice.client.room.Room;
import com.example.company.roomreservationservice.client.room.RoomServiceClient;
import com.example.company.roomreservationservice.data.RoomReservation;
import com.example.company.roomreservationservice.data.RoomReservationDto;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoomReservationService {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private final GuestServiceClient guestServiceClient;
    private final ReservationServiceClient reservationServiceClient;
    private final RoomServiceClient roomServiceClient;
    private final ConversionService conversionService;
    private final RefreshScopedService refreshScopedService;

    public RoomReservationService(GuestServiceClient guestServiceClient, ReservationServiceClient reservationServiceClient, RoomServiceClient roomServiceClient, ConversionService conversionService, RefreshScopedService refreshScopedService) {
        this.guestServiceClient = guestServiceClient;
        this.reservationServiceClient = reservationServiceClient;
        this.roomServiceClient = roomServiceClient;
        this.conversionService = conversionService;
        this.refreshScopedService = refreshScopedService;
    }

    public Collection<RoomReservationDto> getRoomReservations(String dateString) {
        System.out.println(refreshScopedService.getTestProperty());
        if (!StringUtils.hasLength(dateString)) {
            Date date = new Date(System.currentTimeMillis());
            DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            dateString = dateFormat.format(date);
        }
        final String usableDateString = dateString;
        //get all rooms first
        List<Room> rooms = this.roomServiceClient.getAll();
        //now build a room reservation for each one
        Map<Long, RoomReservation> roomReservations = new HashMap<>(rooms.size());
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservation.setBedInfo(room.getBedInfo());
            roomReservation.setName(room.getName());
            roomReservation.setDate(usableDateString);
            roomReservations.put(room.getRoomId(), roomReservation);
        });
        List<Reservation> reservations = this.reservationServiceClient.getAll(usableDateString, null);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setReservationId(reservation.getReservationId());
            roomReservation.setGuestId(reservation.getGuestId());
            Guest guest = this.guestServiceClient.getGuest(roomReservation.getGuestId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });
        return roomReservations.values()
                .stream()
                .map(roomReservation -> conversionService.convert(roomReservation, RoomReservationDto.class))
                .toList();
    }
}

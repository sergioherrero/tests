package com.example.company.roomreservationservice.api;

import com.example.company.roomreservationservice.data.RoomReservation;
import com.example.company.roomreservationservice.data.RoomReservationDto;
import com.example.company.roomreservationservice.service.RoomReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("roomReservations")
public class RoomReservationController {

    private final RoomReservationService roomReservationService;

    public RoomReservationController(RoomReservationService roomReservationService) {
        this.roomReservationService = roomReservationService;
    }

    @GetMapping
    public Collection<RoomReservationDto> getRoomReservations(@RequestParam(value = "date", required = false) String dateString) {
        return roomReservationService.getRoomReservations(dateString);
    }
}

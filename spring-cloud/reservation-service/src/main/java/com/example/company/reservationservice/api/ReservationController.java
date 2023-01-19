package com.example.company.reservationservice.api;

import com.example.company.reservationservice.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDto> getAll(@RequestParam(value = "guestId", required = false) Long guestId, @RequestParam(value = "date", required = false) String dateString) {
        return reservationService.getAll(guestId, dateString);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationDto createReservation(@RequestBody ReservationDto reservation) {
        return reservationService.createReservation(reservation);
    }

    @GetMapping("/{id}")
    public ReservationDto getReservation(@PathVariable("id") long id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateReservation(@PathVariable("id") long id, @RequestBody ReservationDto reservation) {
        reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteReservation(@PathVariable("id") long id) {
        reservationService.deleteReservation(id);
    }

}

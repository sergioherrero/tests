package com.example.company.roomreservationservice.client.reservation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@FeignClient("reservation-service")
public interface ReservationServiceClient {

    @GetMapping("/reservations")
    List<Reservation> getAll(
            @RequestParam(value = "dateString", required = false) String dateString,
            @RequestParam(value = "guestId", required = false) Long guestId);

    @PostMapping("/reservations")
    Reservation addReservation(@RequestBody Reservation reservation);

    @GetMapping("/reservations/{id}")
    Reservation getReservation(@PathVariable("id") long id);

    @PutMapping("/reservations/{id}")
    void updateReservation(@RequestBody Reservation reservation, @PathVariable("id") long id);

    @DeleteMapping("/reservations/{id}")
    void deleteReservation(@PathVariable("id") long id);

}

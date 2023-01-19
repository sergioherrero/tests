package com.example.company.reservationservice.data;

import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Iterable<Reservation> findReservationEntitiesByGuestId(long guestId);

    Iterable<Reservation> findReservationEntitiesByDate(Date date);

    Iterable<Reservation> findReservationEntitiesByDateAndGuestId(Date date, long guestId);
}

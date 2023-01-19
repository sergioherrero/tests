package com.example.company.reservationservice.api;

import com.example.company.reservationservice.data.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDto {

    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private long reservationId;
    private long roomId;
    private long guestId;
    private String date;

    public ReservationDto(Reservation entity) {
        super();
        this.roomId = entity.getRoomId();
        this.guestId = entity.getGuestId();
        Date date = new Date(entity.getDate().getTime());
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.date = dateFormat.format(date);
        this.reservationId = entity.getReservationId();
    }

    @JsonIgnore
    public Reservation getReservationEntity() throws ParseException {
        Reservation entity = new Reservation();
        entity.setReservationId(this.reservationId);
        entity.setGuestId(this.guestId);
        entity.setRoomId(this.roomId);
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        Date date = dateFormat.parse(this.date);
        entity.setDate(new java.sql.Date(date.getTime()));
        return entity;
    }
}

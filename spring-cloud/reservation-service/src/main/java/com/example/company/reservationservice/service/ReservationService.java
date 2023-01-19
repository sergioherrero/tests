package com.example.company.reservationservice.service;

import com.example.company.reservationservice.api.ReservationDto;
import com.example.company.reservationservice.data.Reservation;
import com.example.company.reservationservice.data.ReservationRepository;
import com.example.company.reservationservice.error.BadRequestException;
import com.example.company.reservationservice.error.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final static Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
    public static final String FORMAT_DATE = "dd-MM-yyyy";
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<ReservationDto> getAll(Long guestId, String dateString) {
        Iterable<Reservation> entities;
        List<ReservationDto> reservations = new ArrayList<>();
        if (StringUtils.hasLength(dateString) && guestId != null) {
            try {
                Date date = this.getDateFromString(dateString);
                entities = this.reservationRepository.findReservationEntitiesByDateAndGuestId(date, guestId);
            } catch (ParseException e) {
                LOGGER.error("unable to translate date", e);
                throw new BadRequestException("unable to translate date string");
            }
        } else if (StringUtils.hasLength(dateString)) {
            try {
                Date date = this.getDateFromString(dateString);
                entities = this.reservationRepository.findReservationEntitiesByDate(date);
            } catch (ParseException e) {
                LOGGER.error("unable to translate date", e);
                throw new BadRequestException("unable to translate date string");
            }
        } else if (guestId != null) {
            entities = this.reservationRepository.findReservationEntitiesByGuestId(guestId);
        } else {
            entities = this.reservationRepository.findAll();
        }
        entities.forEach(entity -> reservations.add(new ReservationDto(entity)));
        return reservations;
    }

    public ReservationDto createReservation(ReservationDto reservation) {
        try {
            Reservation entity = reservation.getReservationEntity();
            entity = this.reservationRepository.save(entity);
            return new ReservationDto(entity);
        } catch (ParseException e) {
            throw new BadRequestException("unable to translate date string");
        }
    }

    public ReservationDto getReservation(long id) {
        Optional<Reservation> entity = this.reservationRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("id not found " + id);
        }
        return new ReservationDto(entity.get());
    }

    public void updateReservation(long id, ReservationDto reservation) {
        if (id != reservation.getReservationId()) {
            throw new BadRequestException("id in body doesn't match path");
        }
        try {
            Reservation entity = reservation.getReservationEntity();
            this.reservationRepository.save(entity);
        } catch (ParseException e) {
            throw new BadRequestException("unable to translate date string");
        }
    }

    public void deleteReservation(long id) {
        reservationRepository.deleteById(id);
    }

    private Date getDateFromString(String dateString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        java.util.Date date = dateFormat.parse(dateString);
        return new Date(date.getTime());
    }
}

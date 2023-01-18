package com.example.company.guestservice.infrastructure.output.port.h2;

import com.example.company.guestservice.infrastructure.output.entity.h2.GuestH2;
import org.springframework.data.repository.CrudRepository;

public interface GuestH2Repository extends CrudRepository<GuestH2, Long> {
    Iterable<GuestH2> findGuestsByEmailAddress(String emailAddress);
}

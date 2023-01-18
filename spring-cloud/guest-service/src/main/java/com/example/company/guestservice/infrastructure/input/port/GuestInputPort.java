package com.example.company.guestservice.infrastructure.input.port;

import com.example.company.guestservice.application.dto.GuestDto;

import java.util.List;

public interface GuestInputPort {
    List<GuestDto> getAll(String email);

    GuestDto save(GuestDto guest);

    GuestDto getGuest(Long id);

    void updateGuest(Long id, GuestDto guest);

    void deleteGuest(Long id);
}

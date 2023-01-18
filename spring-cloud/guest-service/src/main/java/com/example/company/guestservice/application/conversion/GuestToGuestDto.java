package com.example.company.guestservice.application.conversion;

import com.example.company.guestservice.application.dto.GuestDto;
import com.example.company.guestservice.domain.model.Guest;
import org.springframework.core.convert.converter.Converter;

public class GuestToGuestDto implements Converter<Guest, GuestDto> {

    @Override
    public GuestDto convert(Guest guest) {
        return GuestDto.builder()
                .guestId(guest.getGuestId())
                .country(guest.getCountry())
                .emailAddress(guest.getEmailAddress())
                .address(guest.getAddress())
                .firstName(guest.getFirstName())
                .lastName(guest.getLastName())
                .phoneNumber(guest.getPhoneNumber())
                .state(guest.getState())
                .build();
    }
}

package com.example.company.guestservice.application.conversion;

import com.example.company.guestservice.application.dto.GuestDto;
import com.example.company.guestservice.infrastructure.output.entity.h2.GuestH2;
import org.springframework.core.convert.converter.Converter;

public class GuestH2ToGuestDto implements Converter<GuestH2, GuestDto> {

    @Override
    public GuestDto convert(GuestH2 guest) {
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

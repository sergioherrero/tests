package com.example.company.guestservice.application.conversion;

import com.example.company.guestservice.application.dto.GuestDto;
import com.example.company.guestservice.infrastructure.output.entity.h2.GuestH2;
import org.springframework.core.convert.converter.Converter;

public class GuestDtoToGuestH2 implements Converter<GuestDto, GuestH2> {

    @Override
    public GuestH2 convert(GuestDto guest) {
        GuestH2 guestH2 = new GuestH2();
        guestH2.setGuestId(guest.getGuestId());
        guestH2.setCountry(guest.getCountry());
        guestH2.setEmailAddress(guest.getEmailAddress());
        guestH2.setAddress(guest.getAddress());
        guestH2.setFirstName(guest.getFirstName());
        guestH2.setLastName(guest.getLastName());
        guestH2.setPhoneNumber(guest.getPhoneNumber());
        guestH2.setState(guest.getState());
        return guestH2;
    }
}

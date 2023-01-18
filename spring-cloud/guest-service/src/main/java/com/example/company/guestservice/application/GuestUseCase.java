package com.example.company.guestservice.application;

import com.example.company.guestservice.application.dto.GuestDto;
import com.example.company.guestservice.error.BadRequestException;
import com.example.company.guestservice.error.NotFoundException;
import com.example.company.guestservice.infrastructure.input.port.GuestInputPort;
import com.example.company.guestservice.infrastructure.output.entity.h2.GuestH2;
import com.example.company.guestservice.infrastructure.output.port.h2.GuestH2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RefreshScope
public class GuestUseCase implements GuestInputPort {

    @Autowired
    GuestH2Repository guestH2Repository;

    @Autowired
    ConversionService convert;

    @Override
    public List<GuestDto> getAll(String emailAddress) {
        Iterable<GuestH2> guestH2Iterable = StringUtils.hasLength(emailAddress) ? this.guestH2Repository.findGuestsByEmailAddress(emailAddress) : this.guestH2Repository.findAll();

        List<GuestDto> list2 = StreamSupport.stream(guestH2Iterable.spliterator(), false)
                .map(guest -> convert.convert(guest, GuestDto.class))
                .collect(Collectors.toList());
        return list2;
    }

    @Override
    public GuestDto save(GuestDto guest) {
        return convert.convert(this.guestH2Repository.save(convert.convert(guest, GuestH2.class)), GuestDto.class);
    }

    @Override
    public GuestDto getGuest(Long id) {
        Optional<GuestH2> guest = this.guestH2Repository.findById(id);
        if (guest.isEmpty()) {
            throw new NotFoundException("id not found: " + id);
        }
        return convert.convert(guest.get(), GuestDto.class);
    }

    @Override
    public void updateGuest(Long id, GuestDto guest) {
        if (id != guest.getGuestId()) {
            throw new BadRequestException("incoming id in body doesn't match path");
        }
        this.guestH2Repository.save(convert.convert(guest, GuestH2.class));
    }

    @Override
    public void deleteGuest(Long id) {
        this.guestH2Repository.deleteById(id);
    }
}

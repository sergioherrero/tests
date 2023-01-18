package com.example.company.guestservice.infrastructure.input.adapter.api;

import com.example.company.guestservice.application.dto.GuestDto;
import com.example.company.guestservice.infrastructure.input.port.GuestInputPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guests")
public class GestAPI {

    final static Logger logger = LoggerFactory.getLogger(GestAPI.class);

    @Autowired
    GuestInputPort guestInputPort;

    @GetMapping
    public List<GuestDto> getGuests(@RequestParam(value = "emailAddress", required = false) String emailAddress) {
        logger.warn("get all guest!");
        return this.guestInputPort.getAll(null);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GuestDto addGuest(@RequestBody GuestDto guest) {
        return this.guestInputPort.save(guest);
    }

    @GetMapping("/{id}")
    public GuestDto getGuest(@PathVariable("id") Long id) {
        return this.guestInputPort.getGuest(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateGuest(@PathVariable("id") Long id, @RequestBody GuestDto guest) {
        this.guestInputPort.updateGuest(id, guest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteGuest(@PathVariable("id") Long id) {
        this.guestInputPort.deleteGuest(id);
    }
}



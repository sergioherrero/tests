package com.example.company.roomreservationservice.client.guest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient("guest-service")
public interface GuestServiceClient {

    @GetMapping("/guests")
    List<Guest> getAll();

    @PostMapping("/guests")
    Guest addGuest(@RequestBody Guest guest);

    @GetMapping("/guests/{id}")
    Guest getGuest(@PathVariable("id") long id);

    @PutMapping("/guests/{id}")
    void updateGuest(@RequestBody Guest guest, @PathVariable("id") long id);

    @DeleteMapping("/guests/{id}")
    void deleteGuest(@PathVariable("id") long id);

}

package com.example.company.roomreservationservice.client.room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@FeignClient("room-service")
public interface RoomServiceClient {

    @GetMapping("/rooms")
    List<Room> getAll();

    @PostMapping("/rooms")
    Room addRoom(@RequestBody Room room);

    @GetMapping("rooms/{id}")
    Room getRoom(@PathVariable("id") long id);

    @PutMapping("/rooms/{id}")
    void updateRoom(@PathVariable("id") long id, @RequestBody Room room);

    @DeleteMapping("/rooms/{id}")
    void deleteRoom(@PathVariable("id") long id);
}

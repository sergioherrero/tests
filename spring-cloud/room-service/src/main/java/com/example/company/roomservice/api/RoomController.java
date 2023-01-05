package com.example.company.roomservice.api;

import com.example.company.roomservice.data.Room;
import com.example.company.roomservice.data.RoomRepository;
import com.example.company.roomservice.error.BadReqeustException;
import com.example.company.roomservice.error.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RefreshScope
@RequestMapping("/rooms")
public class RoomController {

    @Value("${test.property}")
    private String testProperty;

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public Iterable<Room> getAll() {
        System.out.println(testProperty);

        return this.roomRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room addRoom(@RequestBody Room room) {
        return this.roomRepository.save(room);
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        Optional<Room> room = this.roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new NotFoundException("id not found " + id);
        }
        return room.get();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoom(@PathVariable("id") long id, @RequestBody Room room) {
        if (id != room.getRoomId()) {
            throw new BadReqeustException("id in body doesn't match path");
        }
        this.roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable("id") long id) {
        this.roomRepository.deleteById(id);
    }
}

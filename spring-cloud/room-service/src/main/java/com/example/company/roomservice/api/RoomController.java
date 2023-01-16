package com.example.company.roomservice.api;

import com.example.company.roomservice.data.RoomDto;
import com.example.company.roomservice.service.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {


    private final RoomService roomService;

    public RoomController(RoomService roomService) {

        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDto> getAll() {
        return this.roomService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoomDto addRoom(@RequestBody RoomDto room) {
        return roomService.save(room);
    }

    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable("id") long id) {
        return this.roomService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateRoom(@PathVariable("id") long id, @RequestBody RoomDto room) {

        this.roomService.save(room, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.RESET_CONTENT)
    public void deleteRoom(@PathVariable("id") long id) {
        this.roomService.deleteById(id);
    }
}

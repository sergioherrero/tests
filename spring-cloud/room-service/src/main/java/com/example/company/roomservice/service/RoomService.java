package com.example.company.roomservice.service;

import com.example.company.roomservice.data.Room;
import com.example.company.roomservice.data.RoomDto;
import com.example.company.roomservice.error.NotFoundException;
import com.example.company.roomservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RefreshScope
public class RoomService {

    @Value("${test.property}")
    private String testProperty;

    private final RoomRepository roomRepository;

    private final ConversionService convert;


    public RoomService(RoomRepository roomRepository, ConversionService convert) {
        this.roomRepository = roomRepository;
        this.convert = convert;
    }

    public List<RoomDto> findAll() {
        System.out.println(testProperty);
        return StreamSupport.stream(this.roomRepository.findAll().spliterator(), false)
                .map(room -> convert.convert(room, RoomDto.class))
                .toList();
    }

    public RoomDto save(RoomDto roomDto) {
        Room room = new Room();
        room.setRoomId(roomDto.getRoomId());
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setName(roomDto.getName());
        room.setBedInfo(roomDto.getBedInfo());
        return this.convert.convert(this.roomRepository.save(room), RoomDto.class);
    }

    public void save(RoomDto roomDto, long id) {
        Room room = this.findRoom(id);
        room.setRoomId(id);
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setName(roomDto.getName());
        room.setBedInfo(roomDto.getBedInfo());
        this.roomRepository.save(room);
    }

    public void deleteById(long id) {
        this.roomRepository.deleteById(this.findRoom(id).getRoomId());
    }

    public Room findRoom(long id) {
        return this.roomRepository.findById(id).orElseThrow(() -> new NotFoundException("Room id not found " + id));
    }

    public RoomDto findById(long id) {
        return this.convert.convert(this.roomRepository.save(this.findRoom(id)), RoomDto.class);
    }
}

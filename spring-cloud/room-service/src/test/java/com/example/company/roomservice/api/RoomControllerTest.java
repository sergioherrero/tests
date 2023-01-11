package com.example.company.roomservice.api;


import com.example.company.roomservice.data.Room;
import com.example.company.roomservice.data.RoomRepository;
import com.example.company.roomservice.service.RefreshRemoteApplicationEvent;
import io.restassured.mapper.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;



@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/test.properties")
@WebMvcTest(RoomController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class RoomControllerTest {

    @MockBean
    RoomRepository roomRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RefreshRemoteApplicationEvent refreshRemoteApplicationEvent;


    @Test
    public void get_all_rooms_should_return_ok() throws Exception {
        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk());
    }

    @Test
    public void get_by_id_room_should_return_ok() throws Exception {
        long id = 1;
        Room r = new Room();
        r.setName("name");
        r.setRoomNumber("roomNumber");
        r.setRoomId(1);
        r.setBedInfo("info");

        Mockito.when(roomRepository.findById(id)).thenReturn(Optional.of(r));
        var returnResponse = mockMvc.perform(get("/rooms/" + id))
                .andExpect(status().isOk()).andReturn();
        String actualJson = returnResponse.getResponse().getContentAsString();
        String expectedResponse = "{\"roomId\":1,\"roomNumber\":\"roomNumber\",\"bedInfo\":\"info\",\"name\":\"name\"}";
        assertEquals(actualJson, expectedResponse);
    }
}

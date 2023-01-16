package com.example.company.roomservice.api;


import com.example.company.roomservice.data.RoomDto;
import com.example.company.roomservice.service.RoomService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/test.properties")
@WebMvcTest(RoomController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class RoomControllerTest {

    @MockBean
    private RoomService roomService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void get_all_rooms_should_return_ok() throws Exception {
        mockMvc.perform(get("/rooms"))
               .andExpect(status().isOk());
    }

    @Test
    public void get_by_id_room_should_return_ok() throws Exception {

        Mockito.when(roomService.findById(1)).thenReturn(RoomDto.builder()
                .roomId(1)
                .name("name")
                .roomNumber("roomNumber")
                .bedInfo("info")
                .build());
        var returnResponse = mockMvc.perform(get("/rooms/" + 1))
                .andExpect(status().isOk()).andReturn();
        String actualJson = returnResponse.getResponse().getContentAsString();
        String expectedResponse = "{\"roomId\":1,\"roomNumber\":\"roomNumber\",\"bedInfo\":\"info\",\"name\":\"name\"}";
        assertEquals(actualJson, expectedResponse);
    }

    @Test
    public void create_room_should_return_ok() throws Exception {

        RoomDto roomDto = RoomDto.builder()
                .roomId(1)
                .name("name")
                .roomNumber("roomNumber")
                .bedInfo("info")
                .build();

        Mockito.when(roomService.save(roomDto)).thenReturn(roomDto);
        var returnResponse = mockMvc
                .perform(post("/rooms")
                        .content("{\"roomId\":1,\"roomNumber\":\"roomNumber\",\"bedInfo\":\"info\",\"name\":\"name\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();
        String actualJson = returnResponse.getResponse().getContentAsString();
        String expectedResponse = "{\"roomId\":1,\"roomNumber\":\"roomNumber\",\"bedInfo\":\"info\",\"name\":\"name\"}";
        // assertEquals(actualJson, expectedResponse);
    }
}

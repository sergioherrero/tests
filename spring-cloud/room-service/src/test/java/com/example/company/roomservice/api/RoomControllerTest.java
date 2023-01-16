package com.example.company.roomservice.api;


import com.example.company.roomservice.data.RoomDto;
import com.example.company.roomservice.service.RoomService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;



@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = "/test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class RoomControllerTest {

    @MockBean
    private RoomService roomService;

    @Test
    public void get_all_rooms_should_return_ok() {
        given()
                .when()
                .get("/rooms")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void get_by_id_room_should_return_ok() {

        Mockito.when(roomService.findById(1)).thenReturn(RoomDto.builder()
                .roomId(1)
                .name("name")
                .roomNumber("roomNumber")
                .bedInfo("info")
                .build());

        given()
                .when()
                .get("/rooms/" + 1)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void create_room_should_return_ok()  {

        RoomDto roomDto = RoomDto.builder()
                .roomId(1)
                .name("name")
                .roomNumber("roomNumber")
                .bedInfo("info")
                .build();

        Mockito.when(roomService.save(roomDto)).thenReturn(roomDto);
        given()
                .body(roomDto)
                .contentType(ContentType.JSON)
                .when()
                .post("/rooms")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void update_room_should_return_ok() {
        RoomDto roomDto = RoomDto.builder()
                .roomId(1)
                .name("name")
                .roomNumber("roomNumber")
                .bedInfo("info")
                .build();

        given()
                .body(roomDto)
                .contentType(ContentType.JSON)
                .when()
                .put("/rooms/" + 1)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}

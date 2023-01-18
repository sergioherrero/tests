package com.example.company.roomreservationservice.api;

import com.example.company.roomreservationservice.service.RoomReservationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class RoomReservationControllerTest {

    @MockBean
    private RoomReservationService roomReservationService;

    @Test
    public void get_room_Reservations_should_return_ok() {
        given()
                .when()
                .get("/roomReservations")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

}

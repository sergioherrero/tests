package com.example.company.reservationservice.api;


import com.example.company.reservationservice.service.ReservationService;
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
public class ReservationControllerTest {

    @MockBean
    private ReservationService reservationService;

    @Test
    public void get_all_reservations_should_return_ok() {
        given()
                .when()
                .get("/reservations")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void create_room_should_return_created()  {

        ReservationDto reservationDto = getReservationDto();

        Mockito.when(reservationService.createReservation(reservationDto)).thenReturn(reservationDto);
        given()
                .body(reservationDto)
                .contentType(ContentType.JSON)
                .when()
                .post("/reservations")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void when_update_should_return_nothing() {

        given()
                .body(getReservationDto())
                .contentType(ContentType.JSON)
                .when()
                .put("/reservations/" + 1)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    public void when_delete_should_return_nothing() {

        given()
                .body(getReservationDto())
                .contentType(ContentType.JSON)
                .when()
                .delete("/reservations/" + 1)
                .then()
                .statusCode(HttpStatus.RESET_CONTENT.value());
    }

    private ReservationDto getReservationDto() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setReservationId(1);
        reservationDto.setRoomId(1);
        reservationDto.setDate("09/09/1986");
        reservationDto.setGuestId(1);
        return reservationDto;
    }

}

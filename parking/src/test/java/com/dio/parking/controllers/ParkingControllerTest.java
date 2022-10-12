package com.dio.parking.controllers;

import com.dio.parking.model.dto.ParkingCreateDto;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Matches;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//Define teste rodando em porta aleatória
class ParkingControllerTest {

    @LocalServerPort
    private  int randomPort;

    @BeforeEach
    public void setUpTest(){
        RestAssured.port = randomPort;
        RestAssured.baseURI ="http://localhost:"+randomPort+"/parking";
    }

    @Test
    void WhenFindAll() {
         RestAssured.given()
                 .when()
                 .get()
                 .then()
                 .statusCode(HttpStatus.OK.value())
                 .extract().response().body().prettyPeek();

    }

    @Test
    void WhenInsertParking() {
        ParkingCreateDto parkingCreateDto = ParkingCreateDto.builder()
                .color("Red")
                .state("AM")
                .license("AMS-8986")
                .model("FIAT")
                .build();

        RestAssured.given()
                .when()
                .body(parkingCreateDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("license",Matchers.equalTo("AMS-8986"))
                .extract().response().body().prettyPeek();

    }

    @Test
    void WhenFindOne() {

        RestAssured.given()
                .when()
                .get("/bdb7a506a366453f914123e5e8f7e1df")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("model",Matchers.equalTo("Ferrari 360"))
                .body("license",Matchers.equalTo("FFF-0909"))
                .body("color",Matchers.equalTo("Vermelho"))
                .extract().response().body().prettyPeek();
    }

    @Test
    void WhenUpadateParking() {

        ParkingCreateDto parkingCreateDto = ParkingCreateDto.builder()
                .color("Verde")
                .state("SP")
                .license("FFF-0909")
                .model("Ferrari 360")
                .build();

        RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .body(parkingCreateDto)
                .put("/bdb7a506a366453f914123e5e8f7e1df")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract().response().body().prettyPeek();
    }

    @Test
    void WhenDeleteParking() {
       RestAssured.given()
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .when()
               .delete("/77226db070164b4181f9040b139f84f8")
               .then()
               .statusCode(HttpStatus.NO_CONTENT.value())
               .extract().response().body().prettyPeek();
    }

    @Test
    void WhenCheckOut() {
      RestAssured.given()
              .log().all()
              .contentType(MediaType.APPLICATION_JSON_VALUE)
              .when()
              .post("/dbeb8949c1c042f58f8c58cd5645b7de/exit")
              .then()
              .statusCode(HttpStatus.OK.value())
              .extract().response().body().prettyPeek();

    }
}
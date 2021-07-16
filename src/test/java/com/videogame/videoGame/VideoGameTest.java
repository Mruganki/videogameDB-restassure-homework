package com.videogame.videoGame;

import com.videogame.VideoGamePojo;
import com.videogame.testbase.TestBase;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class VideoGameTest extends TestBase {
    public Random randomGenerator = new Random();
    public int randomInt = randomGenerator.nextInt(1000);


    @Test
    //GET
    public void getAllCategories(){
        Response response=
                given()
                        .accept( "application/json" )
                        .when()
                        .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //GET with ID
    public void getSingleProductInfo() {
        Response response =

                given()
                        .accept( "application/json" )
                        .pathParam("id",3)
                        .when()
                        .get("/{id}");
        response.prettyPrint();

    }
    @Test
    //PosT
    public void createNewVideogame(){



        VideoGamePojo videoGamePojo= new VideoGamePojo();
        videoGamePojo.setId(randomInt);
        videoGamePojo.setName("Car Ride Game");
        videoGamePojo.setReleaseDate("2021-07-14T19:24:01.924Z");
        videoGamePojo.setReviewScore(7890);
        videoGamePojo.setCategory("kids");
        videoGamePojo.setRating("5");

        Response response =
                given()
//                        .accept( "application/json" )
                        .header("Content-Type","application/json")
                        .body(videoGamePojo).accept( "application/json" )
                        .when()
                        .post();
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    //PUT

    public void updateExistingVideoGameWithPatch(){

     VideoGamePojo videoGamePojo = new VideoGamePojo();
         videoGamePojo.setId(7);
         videoGamePojo.setName("CARD GAME");
        videoGamePojo.setReleaseDate("2021-07-14T19:24:01.924Z");
        videoGamePojo.setReviewScore(780);
        videoGamePojo.setCategory("kids");
        videoGamePojo.setRating("5");
        Response response =
                given()
                      .accept( "application/json" )
                        .header("Content-Type","application/json")
                        .body(videoGamePojo)
                        .when()
                        .put("/7");
        response.then().statusCode(200);
        response.prettyPrint();

    }
    @Test
    public void deleteProductInfo() {
        Response response =
                given()
                        .accept( "application/json" )
                        .pathParam("id", "1001")
                        .when()
                        .delete("http://localhost:3030/categories/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }



}





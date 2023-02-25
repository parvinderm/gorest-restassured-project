package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
//        RestAssured.port = 8080;
        response = given()
                .when()
                .get(" /public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
    }
    //Verify the if the total record is 25
    @Test
    public void test01(){
        response.body("size",equalTo(25));
    }
    //Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum.”
    @Test
    public void test02(){
        response.body("[1].title",equalTo("Colloco ullam uterque tumultus at cura defigo molestiae audio suscipio utrimque amplus villa aequus defluo."));

    }
    //Check the single user_id in the Array list (5522)
    @Test
    public void test03(){
        response.body("[1].user_id",equalTo(604453));
    }
    //Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test04(){
        response.body("[3].id",equalTo(20962));
        response.body("[4].id",equalTo(20961));
        response.body("[6].id",equalTo(20958));

    }
    @Test
    public void test05(){
        response.body("[2].body",equalTo("Volaticus alveus et. Versus color deleo. Victus teres talis. Tutis sit ars. Esse est demitto. Templum dolorem aequus. Curis ratione provident. Est venustas valens. Iure ascit corrigo. Vir patior crapula. Accedo patior admiratio. Tricesimus cupiditate ancilla. Dedico voluptas color. Blandior capto approbo. Cernuus dens cultellus. Adstringo vobis avarus. Conventus aliquid abscido. Ipsam thorax conduco. Adficio spiritus ut. Ambulo ara et. Quis cursim decor. Tertius delinquo tamdiu."));

    }
}

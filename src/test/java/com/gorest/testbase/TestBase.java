package com.gorest.testbase;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestBase {

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
//        RestAssured.port =8080 ;
        RestAssured.basePath = "/users";
    }


}

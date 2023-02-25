package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCrudTest extends TestBase {
  int idNumber;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath = "/users";
        RestAssured.authentication=RestAssured.basic("username","password");
    }
    @Test //get all list
    public void getAllList(){
        given()
                .when()
                .log().all()
                .get("/?page=1&per_page=20")
                .then().log().all().statusCode(200);
    }
    @Test //post new and retrieve id
    public void test02() {
       UserPojo pojo=new UserPojo();
        pojo.setName("jenn patel");
        pojo.setGender("male");
        pojo.setEmail("dha789@yahh.com");
        pojo.setStatus("active");

        Response response =given()
                .header("Authorization","b8e8e64203d788c7af784ee764c751b8a65a8aa2368279cb88f6549567db8e86")
                .contentType("application/json")
                .body(pojo)
                .post();
        response.then().log().all().statusCode(201);
        response.prettyPrint();

    }

}

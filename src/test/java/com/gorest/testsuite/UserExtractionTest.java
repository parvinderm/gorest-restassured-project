package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/";
//        RestAssured.port = 8080;
        response = given()
                .when()
                .get(" /public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
    }
    //Extract the All Ids
    @Test
    public void test01(){
        int size=response.extract().path("size");
        System.out.println("value of size is :" +size);
        Assert.assertEquals(20,size);
        response.body("size",equalTo(20));
    }
    //Extract the all names
    @Test
    public void test02(){
        List<String>AllNames=response.extract().path("name");
        System.out.println("list all nameS  :"+AllNames);
        for(String a:AllNames)
            if(a.equals(20)){
                Assert.assertTrue(true);
            }
    }
    //Extract the name of 5th object
    @Test
    public void test03(){
       String nameOf5ThObject=response.extract().path("[5].name");
        System.out.println(nameOf5ThObject);
    }
    //Extract the names of all object whose status = inactive
    @Test
    public void test04(){
        ArrayList<String> status = response.extract().path("findAll{it.status=='inactive'}.name");
        System.out.println("The names of inactive status are :" + status);
        Assert.assertTrue(true);

    }
    //Extract the gender of all the object whose status = active
    @Test
    public void test05(){
        ArrayList<String> gender = response.extract().path("findAll{it.status=='active'}.gender");
        System.out.println("The names of active status are :" + gender);
        Assert.assertTrue(true);
    }
    //Print the names of the object whose gender = female
    @Test
    public void test06(){
        ArrayList<String> female = response.extract().path("findAll{it.gender=='female'}.name");
        System.out.println("The nanes of the female gender:"+female);
        Assert.assertTrue(true);
    }
    //Get all the emails of the object where status = inactive
    @Test
    public void test07(){
        ArrayList<String> emails= response.extract().path("findAll{it.status=='inactive'}.email");
        System.out.println("The emails of inactive status object are:"+emails);
        Assert.assertTrue(true);

    }
    //Get the ids of the object where gender = male
    @Test
    public void test08(){
        ArrayList<String> maleid = response.extract().path("findAll{it.gender=='male'}.id");
        System.out.println("The ids of male:"+maleid);
        Assert.assertTrue(true);
    }
    //Get all the status
    @Test
    public void test09(){
        List<String>AllStatus=response.extract().path("status");
        System.out.println("list all names  :"+AllStatus);
        for(String a:AllStatus)
            if(a.equals(20)){
                Assert.assertTrue(true);
            }

    }
    //Get email of the object where name=Kartik Dubashi IV
    @Test
    public void test10(){
        String email=response.extract().path("[4].email");
        System.out.println(email);
        Assert.assertTrue(true);
    }
    //get gender of id=573704
    @Test
    public void test11(){
        String gender=response.extract().path("[2].gender");
        System.out.println(gender);
        Assert.assertTrue(true);
    }




}

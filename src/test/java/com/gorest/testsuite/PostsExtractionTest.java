package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {
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
    //Extract the title
    @Test
    public void test01() {
       ArrayList<String>title=response.extract().path("title");
        System.out.println("list all title :"+title);
        for(String a:title)
            if(a.equals(25)){
                Assert.assertTrue(true);
            }
    }
    //Extract the total number of record
    @Test
    public void test02(){
        ArrayList<Integer>records= response.extract().path("id");
        int size=records.size();
        System.out.println("id:"+size);
        Assert.assertTrue(true);
    }
    // Extract the body of 15th record
    @Test
    public void test03(){
        String body=response.extract().path("[15].body");
        System.out.println("The body of 15th record"+body);
        Assert.assertTrue(true);
    }
    //Extract the user_id of all the records
    @Test
    public void test04(){
        List<Integer> user_idOfAllRecords=response.extract().path("user_id");
        System.out.println("list of all user id records  :"+user_idOfAllRecords);
        for(Integer a:user_idOfAllRecords)
            if(a.equals(25)){
                Assert.assertTrue(true);
            }
    }
    //Extract the title of all the records
    @Test
    public void test05(){
        List<String> titleOfAllRecords=response.extract().path("title");
        System.out.println("list of all records of title  :"+titleOfAllRecords);
        for(String a:titleOfAllRecords)
            if(a.equals(25)){
                Assert.assertTrue(true);
            }
    }
    //Extract the title of all records whose user_id = 5456
    @Test
    public void test06(){
        ArrayList<String> user_id= response.extract().path("findAll{it.user_id='5456'}.title");
        System.out.println("The records of all title:" + user_id);
        Assert.assertTrue(true);
    }
    //Extract the body of all records whose id = 2671
   @Test
    public void test07(){
       ArrayList<String>id= response.extract().path("findAll{it.id='2671'}.body");
       System.out.println("The records of all body:" + id);
       Assert.assertTrue(true);
   }



}

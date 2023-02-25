package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

public class UserAssertionTest {
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
    //1)Verify the if total record is 20
    @Test
    public void test01(){
        response.body("size",equalTo(20));

    }
    //2Verify the if the name of id = 5487 is equal to ”Triloki Nath Mukhopadhyay”
    @Test
    public void test02(){
     response.body("[0].name",equalTo("Triloki Nath Mukhopadhyay"));

    }
    //3Check the single ‘Name’ in the Array list (Meenakshi Adiga)
    @Test
    public void test03(){
        response.body("[1].name",equalTo("Meenakshi Adiga"));

    }
    //4)Check the multiple names in the ArrayList(Karthik Kaniyar,Miss Manikya Deshpande,Chanakya Agarwal)
    @Test
    public void test04(){

        response.body("[3].name",equalTo("Karthik Kaniyar"));
        response.body("[4].name",equalTo("Miss Manikya Deshpande"));
        response.body("[5].name",equalTo("Chanakya Agarwal"));

    }
    //5)Verify the email of userid = 5471 is equal “talwar_anurag@christiansen.io”
    @Test
    public void test05(){
        response.body("[2].email",equalTo("talwar_anurag@christiansen.io"));

    }
    //6)Verify the status is “Active” of user name is “Shanti Bhat V”
    @Test
    public void test06(){
        response.body("[7].status",equalTo("active"));

    }
    //7)Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test07(){
        response.body("[9].gender",equalTo("male"));

    }

}

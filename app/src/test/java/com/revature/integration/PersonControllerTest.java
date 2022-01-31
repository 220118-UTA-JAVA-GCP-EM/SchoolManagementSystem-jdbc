package com.revature.integration;

import com.revature.JavalinApp;
import com.revature.models.Assignment;
import com.revature.models.Person;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonControllerTest {

    private static final JavalinApp app = new JavalinApp();

    @BeforeAll
    public static void startService(){
        app.start(8080);
    }

    @AfterAll
    public static void stopService(){
        app.stop();
    }


    @Test
    public void testGetALlPeople(){
        HttpResponse<List<Person>> response = Unirest.get("http://localhost:8080/people")
                        .asObject(new GenericType<List<Person>>() {});
        assertAll(
                ()->assertEquals( 200,response.getStatus()),
                ()->assertTrue( response.getBody().size()>0));
    }

    @Test
    public void testGetPersonById(){
        HttpResponse<Person> response = Unirest.get("http://localhost:8080/people/3").asObject(Person.class);
        assertAll(
                ()->assertEquals( 200,response.getStatus()),
                ()->assertNotNull( response.getBody()));
    }

    @Test
    public void testGetPersonByInvalidId(){
        HttpResponse<String> response = Unirest.get("http://localhost:8080/people/300").asString();
        assertAll(
                ()->assertEquals( 404,response.getStatus()),
                ()->assertEquals( "Not Found",response.getBody())
        );}

}

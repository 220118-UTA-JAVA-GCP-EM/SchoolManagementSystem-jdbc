package com.revature.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.JavalinApp;
import com.revature.models.Assignment;
import kong.unirest.GenericType;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class AssignmentControllerTest {

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
    public void testGetAssignmentUnauthorized(){
        HttpResponse<String> response = Unirest.get("http://localhost:8080/assignments/3").asString();
        assertAll(
                ()->assertEquals( 401,response.getStatus()),
                ()->assertEquals( "please login and try again",response.getBody()));
    }

    @Test
    public void testGetInvalidAssignmentAuthorized(){
        HttpResponse<String> response = Unirest.get("http://localhost:8080/assignments/300")
                .header("Authorization", "TEACHER-TOKEN")
                .asString();
        assertAll(
                ()->assertEquals( 404,response.getStatus()),
                ()->assertEquals( "Not Found",response.getBody()));
    }

    @Test
    public void testGetValidAssignmentAuthorizedAsStudent(){
        HttpResponse<String> response = Unirest.get("http://localhost:8080/assignments/3")
                .header("Authorization", "STUDENT-TOKEN")
                .asString();

        assertAll(
                ()->assertEquals( 403,response.getStatus()),
                ()->assertEquals( "students are unable to access this feature",response.getBody()));
    }

    @Test
    public void testGetValidAssignmentAuthorizedAsTeacher() throws JsonProcessingException {
        HttpResponse<String> response = Unirest.get("http://localhost:8080/assignments/3")
                .header("Authorization", "TEACHER-TOKEN")
                        .asString();

        ObjectMapper om = new ObjectMapper();
        Assignment a = om.readValue(response.getBody(), Assignment.class);
        System.out.println(response.getBody());
        System.out.println(a);
        assertAll(
                ()-> assertEquals(200, response.getStatus()),
                ()->assertNotNull(response.getBody())
        );
    }
}

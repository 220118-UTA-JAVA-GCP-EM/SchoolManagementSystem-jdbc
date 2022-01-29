package com.revature.controllers;

import com.revature.models.Person;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;

public class AuthController {

    private final PersonService personService = new PersonService();

    public void authenticateLogin(Context ctx){
        // interpret request
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        // fulfill the request
        Person person =  personService.getByUsernameAndPassword(username, password);

        // preparing response
        if(person==null){
            throw new UnauthorizedResponse("Incorrect credentials");
        } else {
            String simpleToken = person.getType()+"-TOKEN"; // "TEACHER-TOKEN" or "STUDENT-TOKEN"
            ctx.header("Authorization", simpleToken);
            ctx.status(200);
        }
    }

    public void authorizeTeacherToken(Context ctx){
        String authHeader = ctx.header("Authorization");
        if(authHeader!=null){
            if(authHeader.equals("TEACHER-TOKEN")){
                return;
            } else if (authHeader.equals("STUDENT-TOKEN")){
                throw new ForbiddenResponse("students are unable to access this feature");
            }
        }
        throw new UnauthorizedResponse("please login and try again");
    }
}

package com.revature.controllers;

import com.revature.models.Person;
import com.revature.services.PersonService;
import io.javalin.http.Context;
import java.util.List;

public class PersonController {

    private final PersonService personService = new PersonService();

    public void handleGetAll(Context ctx){
        List<Person> people = personService.getAll();
        ctx.json(people);
    }

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int id = Integer.parseInt(idParam);
        Person person = personService.getById(id);
        // return a 404 if not found
        ctx.json(person);
    }

    public void handleUpdate(Context ctx){
        // interpret incoming request
        String idParam = ctx.pathParam("id");
        Person personToUpdate = ctx.bodyAsClass(Person.class);
        int idToUpdate = Integer.parseInt(idParam);
        personToUpdate.setPersonId(idToUpdate);

        //fulfill the request
        boolean success = personService.update(personToUpdate);

        //respond to client
        if(success){
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    public void handleCreate(Context ctx){
        // interpret request
        Person newPerson = ctx.bodyAsClass(Person.class);
        boolean success = personService.createPerson(newPerson);

        // prepare response
        if(success){
            ctx.status(201);
        } else {
            ctx.status(400);
        }
    }

    public void handleDelete(Context ctx){
        ctx.status(405);
    }


}

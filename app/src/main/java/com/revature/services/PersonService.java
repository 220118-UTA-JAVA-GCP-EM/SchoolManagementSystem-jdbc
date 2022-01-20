package com.revature.services;

import com.revature.models.Person;
import com.revature.models.Type;

import java.util.Locale;

public class PersonService {

    //Services are what we use to do CRUD functionality, and use to link in between our data layers and our model layers
    //Create
    //Read
    //Update
    //Delete
    //The service class is also where other business logic occurs, we break these up into specific classes

    public Person createPerson(int id, Type t, String first, String last, String password){
        String email = first + "." + last + "@school.edu";
        email = email.toLowerCase();
        Person p = new Person(id, t, first, last, email, password);
        return p;
    }

    //Without a database, other parts of crud don't really work, so we will leave those for next week


}

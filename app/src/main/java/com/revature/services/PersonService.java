package com.revature.services;

import com.revature.daos.PersonDao;
import com.revature.daos.PersonDaoImpl;
import com.revature.models.Person;
import com.revature.models.Type;

import java.util.List;
import java.util.Locale;

public class PersonService {

    private PersonDao personDao = new PersonDaoImpl();

    //Services are what we use to do CRUD functionality, and use to link in between our data layers and our model layers
    //Create
    //Read
    //Update
    //Delete
    //The service class is also where other business logic occurs, we break these up into specific classes

    public boolean createPerson(Type t, String first, String last, String password){
        String email = first + "." + last + "@school.edu";
        email = email.toLowerCase();
        Person p = new Person(t, first, last, email, password);
        return personDao.createPerson(p);
    }

    public List<Person> getAll(){
        return personDao.getAllPeople();
    }

    public boolean changePassword(String oldPass, String newPass, int id){
        // obtain the record from the database
        Person person = personDao.getPersonById(id);

        // compare the previous password to the password saved in the database
        if(person.getPassword().equals(oldPass)){

            // update the record with the new password
            person.setPassword(newPass);
            boolean updateSuccess = personDao.updatePerson(person);
            return updateSuccess;
        }
        return false;
    }

    public Person getById(int id){
        return personDao.getPersonById(id);
    }

}

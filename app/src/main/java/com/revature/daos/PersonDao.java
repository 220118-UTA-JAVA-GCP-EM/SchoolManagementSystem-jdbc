package com.revature.daos;

import com.revature.models.Person;

import java.util.List;

public interface PersonDao {

    public boolean createPerson(Person p);
    public List<Person> getAllPeople();
    //other crud methods

}

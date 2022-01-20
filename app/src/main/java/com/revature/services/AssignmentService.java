package com.revature.services;

import com.revature.models.Assignment;
import com.revature.models.Person;
import org.checkerframework.checker.units.qual.A;

import java.sql.Date;

public class AssignmentService {

    //We need logic to
    //Create assignments, turn in assignments, and grade assignments

    public Assignment createNewAssignment(int id, Person s, Date due){

        Assignment a = new Assignment(id, s, 0.0, false, false, due);

        return a;

    }

    public void turnInAssignment(Assignment a){
        long today = System.currentTimeMillis();
        Date turnInDate = new Date(today);

        if(turnInDate.compareTo(a.getDue()) > 0){
            a.setPastDue(true);
            a.setDone(true);
        }

        a.setDone(true);
    }

    public void gradeAssignment(double grade, Assignment a){
        if(a.isPastDue()){
            //If the student turned in the assignment late, it gets a deduction
            grade = grade * .8;
            a.setGrade(grade);
        } else {
            a.setGrade(grade);
        }
    }

}

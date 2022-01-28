package com.revature.services;

import com.revature.daos.AssignmentDao;
import com.revature.daos.AssignmentDaoImpl;
import com.revature.models.Assignment;
import com.revature.models.Person;
import org.checkerframework.checker.units.qual.A;

import java.sql.Date;

public class AssignmentService {

    private AssignmentDao assignmentDao = new AssignmentDaoImpl();

    public Assignment getAssignmentById(int assignmentId){
        return assignmentDao.getAssignmentById(assignmentId);
    }

    //the following methods are no longer in use, now that were using a db to represent our data and thus have been deprecated
    @Deprecated
    public Assignment createNewAssignment(int id, Person s, Date due){

        Assignment a = new Assignment(id, s, 0.0, false, false, due);
        return a;

    }

    @Deprecated
    public void turnInAssignment(Assignment a){
        long today = System.currentTimeMillis();
        Date turnInDate = new Date(today);

        if(turnInDate.compareTo(a.getDue()) > 0){
            a.setPastDue(true);
            a.setDone(true);
        }

        a.setDone(true);
    }

    @Deprecated
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

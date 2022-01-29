package com.revature.services;

import com.revature.daos.AssignmentDao;
import com.revature.daos.AssignmentDaoImpl;
import com.revature.models.Assignment;
import com.revature.models.Person;
import org.checkerframework.checker.units.qual.A;

import java.sql.Date;

public class AssignmentService {

    private final AssignmentDao assignmentDao = new AssignmentDaoImpl();

    public Assignment getAssignmentById(int assignmentId){
        return assignmentDao.getAssignmentById(assignmentId);
    }

}

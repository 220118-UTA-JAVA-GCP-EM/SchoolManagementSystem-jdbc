package com.revature.controllers;

import com.revature.models.Assignment;
import com.revature.services.AssignmentService;
import io.javalin.http.Context;

public class AssignmentController {

    private AssignmentService assignmentService = new AssignmentService();

    public void handleGetOne(Context ctx){
        String idParam = ctx.pathParam("id");
        int assignmentId = Integer.parseInt(idParam);
        Assignment assignment = assignmentService.getAssignmentById(assignmentId);
        if(assignment!=null){
            ctx.json(assignment);
        } else {
            ctx.status(404);
        }
    }
}

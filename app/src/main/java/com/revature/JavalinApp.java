package com.revature;

import com.revature.controllers.AppExceptionHandler;
import com.revature.controllers.AssignmentController;
import com.revature.controllers.AuthController;
import com.revature.controllers.PersonController;
import com.revature.util.LoggingUtil;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;

public class JavalinApp {

    private final PersonController personController = new PersonController();
    private final LoggingUtil loggingUtil = new LoggingUtil();
    private final AssignmentController assignmentController = new AssignmentController();
    private final AppExceptionHandler appExceptionHandler = new AppExceptionHandler();
    private final AuthController authController = new AuthController();

    private Javalin app = Javalin.create().routes(()->{
        path("people",()->{
            get(personController::handleGetAll);
            post(personController::handleCreate);
            delete(personController::handleDelete);
            path("{id}",()->{
                get(personController::handleGetOne);
                put(personController::handleUpdate);
            });
        });
        path("assignments", ()->{
            before(authController::authorizeTeacherToken);
            get("{id}", assignmentController::handleGetOne);
        });
        path("login", ()->{
            post(authController::authenticateLogin);
        });
        before("*",loggingUtil::logRequest);
    }).exception(NumberFormatException.class, appExceptionHandler::handleNumberFormatException);

    public void start(int port){
        app.start(port);
    }


}

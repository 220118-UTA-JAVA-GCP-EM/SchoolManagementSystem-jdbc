package com.revature.services;

import com.revature.models.Course;
import com.revature.models.Person;
import com.revature.models.Type;

import java.util.Collections;
import java.util.List;
//the following methods are no longer in use, now that were using a db to represent our data and thus this class has been deprecated
@Deprecated
public class CourseService {

    //Our school will need the following logic for courses
    //Created a course
    //Assign a teacher to a course
    //Add a student student
    //Add topics

    public Course createNewCourse(int id, String subject, int number, String name){

        Course c = new Course();
        c.setCourseId(id);
        c.setSubject(subject);
        c.setNumber(number);
        c.setName(name);

        return c;
    }

    public void assignTeacher(Course c, Person t){
        if(t.getType() != Type.TEACHER){
            //Do some logic for this
        }

        c.setTeacher(t);
    }

    public void addStudent(Course c, Person... p){
        List<Person> students = c.getStudents();

        //Cool method from collections that does a lot of work for us
        Collections.addAll(students, p);
    }

    public void addTopic(Course c, String... topic){
        List<String> topicList = c.getTopics();

        Collections.addAll(topicList, topic);

    }

}

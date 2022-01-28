package com.revature.daos;

import com.revature.models.Assignment;
import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AssignmentDaoImpl implements AssignmentDao{


    @Override
    public Assignment getAssignmentById(int id) {
        try (Connection c = ConnectionUtil.getConnection();){
            PreparedStatement s = c.prepareStatement("select * from {oj assignment left join person on person.id = assignment.student where assignment.id = ? } ");
            s.setInt(1, id);
            ResultSet rs = s.executeQuery();
            if(rs.next()){
                Assignment a = new Assignment();
                a.setAssignmentId(rs.getInt("id"));
                int studentId = rs.getInt("student");
                if(studentId!=0){
                    Person student = new Person();
                    student.setPersonId(rs.getInt("student"));
                    student.setType(Type.values()[rs.getInt("type")]);
                    student.setFirst(rs.getString("first"));
                    student.setLast(rs.getString("last"));
                    student.setEmail(rs.getString("email"));
                    student.setPassword(rs.getString("password"));
                    a.setStudent(student);
                }
                a.setGrade(rs.getDouble("grade"));
                a.setDone(rs.getBoolean("done"));
                a.setPastDue(rs.getBoolean("past_due"));
                a.setDue(rs.getDate("due_date"));
                return a;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}

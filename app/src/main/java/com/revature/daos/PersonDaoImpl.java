package com.revature.daos;

import com.revature.models.Person;
import com.revature.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    @Override
    public boolean createPerson(Person p) {
        String sql = "insert into person (type, first, last, email, password) values (?, ?, ?, ?, ?)";
        try {
            Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);
            // set type as param index 1
            ps.setInt(1, p.getType().ordinal());

            // set first as param index 2  -- set the name using the person object (p)
            ps.setString(2, p.getFirst());
            ps.setString(3, p.getLast());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getPassword());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Person> getAllPeople() {
        return null;
    }
}

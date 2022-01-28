package com.revature.daos;

import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    @Override
    public boolean createPerson(Person p) {
        String sql = "insert into person (type, first, last, email, password) values (?, ?, ?, ?, ?)";

        // try with resources allows us to declare Autoclosable resources so that they are
            // automatically closed at the end of the try block
        try (Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);){

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
        String sql = "select * from person";
        List<Person> people = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement();){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                Person person = new Person();
                int id = rs.getInt("id"); // id value of the current record in the database
                person.setPersonId(id);

                // get the 0/1 ordinal value that is stored in the database
                int typeOrdinal = rs.getInt("type");
                // obtain the values in the ENUM, in an array format where their ordinal corresponds
                // with their position in the array
                Type[] types = Type.values(); //["TEACHER", "STUDENT"]
                // access the appropriate type using the array and ordinal value
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
                // add person to my arraylist
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    private Person processPersonResult(ResultSet rs){
        // process the person result from the RS
        return null;
    }

    @Override
    public Person getPersonById(int id) {
        String sql = "select * from person where id = ? ";
        try (Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Person person = new Person();
                person.setPersonId(id);

                // get the 0/1 ordinal value that is stored in the database
                int typeOrdinal = rs.getInt("type");
                // obtain the values in the ENUM, in an array format where their ordinal corresponds
                // with their position in the array
                Type[] types = Type.values(); //["TEACHER", "STUDENT"]
                // access the appropriate type using the array and ordinal value
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
                return person;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePerson(Person p) {
        String sql = "update person set type = ?, first = ?, last = ?, email = ?, password = ? where id = ?";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1, p.getType().ordinal());
            ps.setString(2, p.getFirst());
            ps.setString(3, p.getLast());
            ps.setString(4, p.getEmail());
            ps.setString(5, p.getPassword());
            ps.setInt(6, p.getPersonId());

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public Person getPersonByUsernameAndPassword(String email, String password) {
        String sql = "select * from person where email = ? and password = ? ";
        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                Person person = new Person();
                person.setPersonId(rs.getInt("id"));

                // get the 0/1 ordinal value that is stored in the database
                int typeOrdinal = rs.getInt("type");
                // obtain the values in the ENUM, in an array format where their ordinal corresponds
                // with their position in the array
                Type[] types = Type.values(); //["TEACHER", "STUDENT"]
                // access the appropriate type using the array and ordinal value
                person.setType(types[typeOrdinal]);

                person.setFirst(rs.getString("first"));
                person.setLast(rs.getString("last"));
                person.setEmail(rs.getString("email"));
                person.setPassword(rs.getString("password"));
                return person;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

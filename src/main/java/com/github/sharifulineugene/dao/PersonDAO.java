package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Account;
import com.github.sharifulineugene.entity.Card;
import com.github.sharifulineugene.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonDAO implements DAOInterface<Person> {
    private static final String jdbc = "jdbc:h2:mem:test";
    @Override
    public List<Person> showAll() {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("select * from person"))
        {
            ResultSet rs = st.executeQuery();
            return extractionFromResultSetWithAllColumns(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    @Override
    public boolean insertIntoTable(Person person) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("insert into person(name,surname,date_of_birth) values(?,?,?)"))
        {
            st.setString(1,person.getName());
            st.setString(2, person.getSurname());
            st.setString(3,person.getDateOfBirth());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\n"+person+" не был добавлен в таблицу...");
            return false;
        }
        return true;
    }

    @Override
    public boolean insertIntoTable(Collection<Person> obj) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("insert into person(name,surname,date_of_birth) values(?,?,?)"))
        {
            for(Person person : obj) {
                st.setString(1, person.getName());
                st.setString(2, person.getSurname());
                st.setString(3, person.getDateOfBirth());
                st.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\n"+obj+" не был добавлен в таблицу...");
            return false;
        }
        return true;
    }


    public boolean updateTable(Person obj) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("update person set name='?', surname='?', date_of_birth='?' where id='?'"))
        {
            st.setString(1,obj.getName());
            st.setString(2,obj.getSurname());
            st.setString(3, obj.getDateOfBirth());
            st.setInt(4,obj.getId());
            st.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Person> extractionFromResultSetWithAllColumns(ResultSet rs) {
        List<Person> list = new ArrayList<>();
        try{
            while(rs.next()) {
                list.add(new Person(rs.getInt(1),rs.getString(2),
                        rs.getString(3),rs.getString(4)));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return list;
    }

    @Override
    public Person getEntityById(int id) {
        Person person = null;
        try(Connection connection = DriverManager.getConnection(jdbc);
            PreparedStatement st = connection.prepareStatement("select * from person where id='?'")) {
            st.setInt(1,id);
            person = extractionFromResultSetWithAllColumns(st.executeQuery()).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\nОбъект Person не найден по ID="+id+"...");
            System.exit(1);
        }
        return person;
    }
}

package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Account;
import com.github.sharifulineugene.entity.Card;
import com.github.sharifulineugene.entity.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccountDAO implements DAOInterface<Account>{
    private static final String jdbc = "jdbc:h2:mem:test";
    @Override
    public List<Account> showAll() {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("select * from account"))
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
    public boolean insertIntoTable(Account account) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("insert into account(number,person_id) values(?,?)"))
        {
            st.setString(1, account.getNumber());
            st.setInt(2, account.getOwner().getId());
            st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\n"+account+" не был добавлен в таблицу...");
            return false;
        }
        return true;
    }

    @Override
    public boolean insertIntoTable(Collection<Account> obj) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("insert into account(number,person_id) values(?,?)"))
        {
            for(Account account : obj) {
                st.setString(1, account.getNumber());
                st.setInt(2, account.getOwner().getId());
                st.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\n"+obj+" не был добавлен в таблицу...");
            return false;
        }
        return true;
    }

    public List<Account> extractionFromResultSetWithAllColumns(ResultSet rs) {
        List<Account> list = new ArrayList<>();
        try{
            while(rs.next()) {
                int person_id = rs.getInt(3);
                PersonDAO personDAO = new PersonDAO();
                Person person = personDAO.getEntityById(person_id);
                list.add(new Account(rs.getInt(1),rs.getString(2),person));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return list;
    }

    @Override
    public Account getEntityById(int id) {
        Account account = null;
        try(Connection connection = DriverManager.getConnection(jdbc);
            PreparedStatement st = connection.prepareStatement("select * from account where id='?'")) {
            st.setInt(1,id);
            account = extractionFromResultSetWithAllColumns(st.executeQuery()).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\nОбъект Account не найден по ID="+id+"...");
            System.exit(1);
        }
        return account;
    }
}

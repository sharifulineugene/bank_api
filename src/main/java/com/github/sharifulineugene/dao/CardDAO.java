package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Account;
import com.github.sharifulineugene.entity.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CardDAO implements DAOInterface<Card> {
    private static final String jdbc = "jdbc:h2:mem:test";
    public List<Card> showAll() {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("select * from card"))
        {
            ResultSet rs = st.executeQuery();
            return extractionFromResultSetWithAllColumns(rs);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.exit(1);
        }
        return null;
    }

    public boolean insertIntoTable(Card card) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("insert into card(card_number,exp_date,account_id) values(?,?,?)"))
        {
                st.setString(1,card.getCardNumber());
                st.setString(2, card.getExpDate());
                st.setInt(3,card.getAccount().getId());
                st.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\n"+card+" не был добавлен в таблицу...");
            return false;
        }
        return true;
    }

    @Override
    public boolean insertIntoTable(Collection<Card> obj) {
        try(Connection c = DriverManager.getConnection(jdbc);
            PreparedStatement st = c.prepareStatement("insert into card(card_number,exp_date,account_id) values(?,?,?)"))
        {
            for(Card card : obj) {
                st.setString(1, card.getCardNumber());
                st.setString(2, card.getExpDate());
                st.setInt(3, card.getAccount().getId());
                st.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\n"+obj+" не был добавлен в таблицу...");
            return false;
        }
        return true;
    }

    public List<Card> extractionFromResultSetWithAllColumns(ResultSet rs) {
        List<Card> list = new ArrayList<>();
        try{
            while(rs.next()) {
                int account_id = rs.getInt(4);
                AccountDAO accountDAO = new AccountDAO();
                Account account = accountDAO.getEntityById(account_id);
                list.add(new Card(rs.getInt(1),rs.getString(2),
                        rs.getString(3),account));
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return list;
    }

    @Override
    public Card getEntityById(int id) {
        Card card = null;
        try(Connection connection = DriverManager.getConnection(jdbc);
            PreparedStatement st = connection.prepareStatement("select * from card where id='?'")) {
            st.setInt(1,id);
            card = extractionFromResultSetWithAllColumns(st.executeQuery()).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.err.println("\n\n\nОбъект Card не найден по ID="+id+"...");
            System.exit(1);
        }
        return card;
    }


}

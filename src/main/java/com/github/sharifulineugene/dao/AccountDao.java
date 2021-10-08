package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.dto.Balance;
import com.github.sharifulineugene.entity.Account;
import com.github.sharifulineugene.entity.Card;
import com.github.sharifulineugene.excception_handling.account.NoSuchAccountException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDao implements IAccountDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public AccountDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Account> index() {
        Session session = sessionFactory.getCurrentSession();
        Query<Account> query = session.createQuery("select a from Account a", Account.class);
        List<Account> all = query.getResultList();
        return all;
    }

    @Override
    public void save(Account object) {
        Session session = sessionFactory.getCurrentSession();
        List<Card> cards = object.getCards();
        if(cards != null)
        for(Card card: cards) {
            if(card == null) continue;
            card.setAccount(object);
            session.save(card);
        }
        session.persist(object);
    }

    @Override
    public void update(Account object) {
        throw new UnsupportedOperationException("method not supported yet");
    }

    @Override
    public Account show(int account_id) {
        Session session = sessionFactory.getCurrentSession();
        Account account = session.get(Account.class, account_id);
        if(account == null) {
            throw new NoSuchAccountException("There is no account with ID = "
                    +account_id+" in database");
        }
        return account;

    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("method not supported yet");
    }

    @Override
    public List<Card> getCardsByAccountId(int account_id) {
        Account account = this.show(account_id);
        if(account == null) {
            throw new NoSuchAccountException("There is no account with ID = "
                    +account_id+" in database");
        }
        List<Card> cards = account.getCards();
        return cards;
    }

    @Override
    public void addCardToAccount(Card card, int account_id) {
        Session session = sessionFactory.getCurrentSession();
        Account account = show(account_id);
        account.addCardToAccount(card);
        card.setAccount(account);
        session.save(card);
        session.update(account);
    }

    @Override
    public void addBalance(int id, Balance deposit) {
        Session session = sessionFactory.getCurrentSession();
        Account account = show(id);
        account.setBalance(account.getBalance()+deposit.getBalance());
        session.update(account);
    }
}

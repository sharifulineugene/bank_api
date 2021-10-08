package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Card;
import com.github.sharifulineugene.excception_handling.card.NoSuchCardException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CardDao implements ICardDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CardDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Card> index() {
        Session session = sessionFactory.getCurrentSession();
        Query<Card> query = session.createQuery("select c from Card c", Card.class);
        List<Card> all = query.getResultList();
        return all;
    }

    @Override
    public void save(Card object) {
        Session session = sessionFactory.getCurrentSession();
        session.save(object);
    }

    @Override
    public void update(Card object) {
        throw new UnsupportedOperationException("method not supported yet");
    }

    @Override
    public Card show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Card card = session.get(Card.class,id);
        if(card == null)
            throw new NoSuchCardException("There is no card with ID = "
                    +id+" in database");
        return card;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("method not supported yet");
    }
}

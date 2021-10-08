package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Person;
import com.github.sharifulineugene.excception_handling.person.NoSuchPersonException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDao implements IPersonDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        Query<Person> query = session.createQuery("select p from Person p", Person.class);
        List<Person> all = query.getResultList();
        return all;
    }

    @Override
    public void save(Person object) {
        Session session = sessionFactory.getCurrentSession();
        session.save(object);
    }

    @Override
    public void update(Person object) {
        throw new UnsupportedOperationException("method not supported yet");
    }

    @Override
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        if(person == null) {
            throw new NoSuchPersonException("There is no person with ID = "
                    +id+" in database");
        }
        return person;
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("method not supported yet");
    }


}

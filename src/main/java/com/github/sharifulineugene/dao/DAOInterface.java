package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Account;
import com.github.sharifulineugene.entity.Card;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.List;

public interface DAOInterface<T> {
    List<T> showAll();
    boolean insertIntoTable(T obj);
    boolean insertIntoTable(Collection<T> obj);
    List<T> extractionFromResultSetWithAllColumns(ResultSet rs);
    T getEntityById(int id);

}

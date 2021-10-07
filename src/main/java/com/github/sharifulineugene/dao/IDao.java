package com.github.sharifulineugene.dao;

import com.github.sharifulineugene.entity.Account;

import java.util.List;

public interface IDao<T> {
    List<T> index();
    void save(T object);
    T show(int id);
    void delete(int id);
    void update(T object);
}

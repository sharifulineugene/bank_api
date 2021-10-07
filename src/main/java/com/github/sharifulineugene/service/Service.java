package com.github.sharifulineugene.service;

import java.util.List;

public interface Service<T> {
    List<T> getAll();
    void save(T object);
    T get(int id);
    void delete(int id);
    void update(T object);
}

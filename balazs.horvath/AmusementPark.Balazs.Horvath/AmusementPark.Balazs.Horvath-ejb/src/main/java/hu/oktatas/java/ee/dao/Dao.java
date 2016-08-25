package hu.oktatas.java.ee.dao;

import java.io.Serializable;

public interface Dao<T> extends Serializable {
    T create(T entity);
    T find(Long id);
    T update(T entity);
    T delete(T entity); 
}

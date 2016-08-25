package hu.oktatas.java.ee.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractDao<T extends Serializable> implements Dao<T> {
    
    protected final Class<T> clazz;
    
    @PersistenceContext(unitName = "amusement-park-PU")
    protected EntityManager entityManager;

    public AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public T find(Long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    @Override
    public T delete(T entity) {
        entityManager.remove(entity);
        return entity;
    }
}

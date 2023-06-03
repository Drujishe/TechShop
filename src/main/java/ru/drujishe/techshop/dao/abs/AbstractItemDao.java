package ru.drujishe.techshop.dao.abs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class AbstractItemDao {
    @PersistenceContext
    protected EntityManager manager;
}

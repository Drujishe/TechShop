package ru.drujishe.techshop.dao.abs;

import ru.drujishe.techshop.model.abs.AbstractItem;

import java.util.List;

public interface ItemDao<T extends AbstractItem> {
    T save(T item);

    T update(T item);

    T findBySerialId(int serialId);

    void delete(T item);

    List<T> findAll();

}

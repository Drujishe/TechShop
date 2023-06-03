package ru.drujishe.techshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.techshop.dao.abs.AbstractItemDao;
import ru.drujishe.techshop.dao.abs.ItemDao;
import ru.drujishe.techshop.model.Computer;
import ru.drujishe.techshop.util.GetSingleResultOrEmpty;

import java.util.List;

@Repository("computerDao")
public class ComputerDaoImpl extends AbstractItemDao implements ItemDao<Computer> {

    @Override
    @Transactional
    public Computer save(Computer item) {
        manager.persist(item);
        return item;
    }

    @Override
    @Transactional
    public Computer update(Computer item) {
        return manager.merge(item);
    }

    @Override
    public Computer findBySerialId(int serialId) {
        var query = manager.createQuery("select c from Computer c where serial = :serial", Computer.class)
                .setParameter("serial", serialId);
        return GetSingleResultOrEmpty.getResult(query).orElse(null);
    }

    @Override
    public void delete(Computer item) {
        manager.remove(item);
    }

    @Override
    public List<Computer> findAll() {
        return manager.createQuery("select c from Computer c", Computer.class).getResultList();
    }
}

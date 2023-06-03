package ru.drujishe.techshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.techshop.dao.abs.AbstractItemDao;
import ru.drujishe.techshop.dao.abs.ItemDao;
import ru.drujishe.techshop.model.Monitor;
import ru.drujishe.techshop.util.GetSingleResultOrEmpty;

import java.util.List;

@Repository("monitorDao")
public class MonitorDaoImpl extends AbstractItemDao implements ItemDao<Monitor> {
    @Override
    @Transactional
    public Monitor save(Monitor item) {
        manager.persist(item);
        return item;
    }

    @Override
    @Transactional
    public Monitor update(Monitor item) {
        return manager.merge(item);
    }

    @Override
    public Monitor findBySerialId(int serialId) {
        var query = manager.createQuery("select m from Monitor m where serial = :serial", Monitor.class)
                .setParameter("serial", serialId);
        return GetSingleResultOrEmpty.getResult(query).orElse(null);
    }

    @Override
    public void delete(Monitor item) {
        manager.remove(item);
    }

    @Override
    public List<Monitor> findAll() {
        return manager.createQuery("select m from Monitor m", Monitor.class).getResultList();
    }

}

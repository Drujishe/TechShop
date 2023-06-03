package ru.drujishe.techshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.techshop.dao.abs.AbstractItemDao;
import ru.drujishe.techshop.dao.abs.ItemDao;
import ru.drujishe.techshop.model.HDD;
import ru.drujishe.techshop.util.GetSingleResultOrEmpty;

import java.util.List;

@Repository("hddDao")
public class HDDDaoImpl extends AbstractItemDao implements ItemDao<HDD> {
    @Override
    @Transactional
    public HDD save(HDD item) {
        manager.persist(item);
        return item;
    }

    @Override
    @Transactional
    public HDD update(HDD item) {
        return manager.merge(item);
    }

    @Override
    public HDD findBySerialId(int serialId) {
        var query = manager.createQuery("select h from HDD h where serial = :serial", HDD.class)
                .setParameter("serial", serialId);
        return GetSingleResultOrEmpty.getResult(query).orElse(null);
    }

    @Override
    public void delete(HDD item) {
        manager.remove(item);
    }

    @Override
    public List<HDD> findAll() {
        return manager.createQuery("select h from HDD h", HDD.class).getResultList();
    }
}

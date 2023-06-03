package ru.drujishe.techshop.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.drujishe.techshop.dao.abs.AbstractItemDao;
import ru.drujishe.techshop.dao.abs.ItemDao;
import ru.drujishe.techshop.model.Notebook;
import ru.drujishe.techshop.util.GetSingleResultOrEmpty;

import java.util.List;

@Repository("notebookDao")
public class NotebookDaoImpl extends AbstractItemDao implements ItemDao<Notebook> {
    @Override
    @Transactional
    public Notebook save(Notebook item) {
        manager.persist(item);
        return item;
    }

    @Override
    @Transactional
    public Notebook update(Notebook item) {
        return manager.merge(item);
    }

    @Override
    public Notebook findBySerialId(int serialId) {
        var query = manager.createQuery("select nb from Notebook nb where serial = :serial", Notebook.class)
                .setParameter("serial", serialId);
        return GetSingleResultOrEmpty.getResult(query).orElse(null);
    }

    @Override
    public void delete(Notebook item) {
        manager.remove(item);
    }

    @Override
    public List<Notebook> findAll() {
        return manager.createQuery("select nb from Notebook nb", Notebook.class).getResultList();
    }
}

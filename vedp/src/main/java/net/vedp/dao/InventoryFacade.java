
package net.vedp.dao;

import java.util.List;

import javax.ejb.Stateless;

import net.vedp.dao.base.BaseDAO;
import net.vedp.entities.Inventory;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Stateless
public class InventoryFacade extends BaseDAO{
    public Inventory create(Inventory entity) {
        em.persist(entity);
        return entity;
    }

    public Inventory update(Inventory entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(Inventory entity) {
        em.remove(em.merge(entity));
    }

    public Inventory find(Object id) {
        return em.find(Inventory.class, id);
    }


    public List<Inventory> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Inventory.class));
        return em.createQuery(cq).getResultList();
    }
}


package net.vedp.dao;


import java.util.List;

import javax.ejb.Stateless;

import net.vedp.dao.base.BaseDAO;
import net.vedp.entities.User;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Stateless
public class UserFacade extends BaseDAO{
    public User create(User entity) {
        em.persist(entity);
        return entity;
    }
    

    public User update(User entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(User entity) {
        em.remove(em.merge(entity));
    }

    public User find(Object id) {
        return em.find(User.class, id);
    }

    
    public List<User> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(User.class));
        return em.createQuery(cq).getResultList();
    }

}

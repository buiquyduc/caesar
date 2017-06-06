
package com.casa.erp.dao;


import java.util.List;

import javax.ejb.Stateless;

import com.casa.erp.dao.base.BaseDAO;
import com.casa.erp.entities.ProductUomCategory;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Stateless
public class ProductUomCategoryFacade extends BaseDAO{
    public ProductUomCategory  create(ProductUomCategory  entity) {
        em.persist(entity);
        return entity;
    }
    

    public ProductUomCategory  update(ProductUomCategory  entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(ProductUomCategory  entity) {
        em.remove(em.merge(entity));
    }

    public ProductUomCategory  find(Object id) {
        return em.find(ProductUomCategory .class, id);
    }

    
    public List<ProductUomCategory > findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(ProductUomCategory.class));
        return em.createQuery(cq).getResultList();
    }

}

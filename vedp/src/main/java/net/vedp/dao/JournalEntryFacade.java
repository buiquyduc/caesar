
package net.vedp.dao;

import java.util.List;

import javax.ejb.Stateless;

import net.vedp.dao.base.BaseDAO;
import net.vedp.entities.JournalEntry;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Stateless
public class JournalEntryFacade extends BaseDAO{
    public JournalEntry find(Object id) {
        return em.find(JournalEntry.class, id);
    }
    

    public List<JournalEntry> findByPartner(Integer partnerId) {
        List<JournalEntry> JournalEntriesByPartner = em.createNamedQuery("JournalEntry.findByPartner")
                .setParameter("partnerId", partnerId)
                .getResultList();
        
        return JournalEntriesByPartner;  
    }
 
    public List<JournalEntry> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(JournalEntry.class));     
        return em.createQuery(cq).getResultList();
    }

}

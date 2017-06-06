
package net.vedp.dao;

import java.util.List;

import javax.ejb.Stateless;

import net.vedp.beans.util.IdGenerator;
import net.vedp.dao.base.BaseDAO;
import net.vedp.entities.Account;
import net.vedp.entities.DeliveryOrder;
import net.vedp.entities.Invoice;
import net.vedp.entities.Journal;
import net.vedp.entities.Partner;
import net.vedp.entities.Product;
import net.vedp.entities.SaleOrder;
import net.vedp.entities.SaleOrderLine;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Stateless
public class SaleOrderFacade extends BaseDAO{
    private IdGenerator idGeerator = new IdGenerator();

    public SaleOrder create(SaleOrder entity) {
        em.persist(entity);
        em.flush();
        entity.setName(idGeerator.generateSaleId(entity.getId()));
        return entity;
    }

    public void create(DeliveryOrder entity) {
        em.persist(entity);
        em.flush();
        entity.setName(idGeerator.generateDeliveryOutId(entity.getId()));

    }

    public Invoice create(Invoice entity) {
        em.persist(entity);
        em.flush();
        entity.setName(idGeerator.generateInvoiceOutId(entity.getId()));
        return entity;

    }

    public SaleOrder update(SaleOrder entity) {
        em.merge(entity);
        return entity;
    }

    public Invoice update(Invoice entity) {
        em.merge(entity);
        return entity;
    }

    public DeliveryOrder update(DeliveryOrder entity) {
        em.merge(entity);
        return entity;
    }

    public void remove(SaleOrder entity) {
        em.remove(em.merge(entity));
    }

    public SaleOrder find(Object id) {
        return em.find(SaleOrder.class, id);
    }

    public SaleOrderLine findOrderLine(Object id) {
        return em.find(SaleOrderLine.class, id);
    }

    public Account findAccount(Object name) {

        List<Account> accounts = em.createNamedQuery("Account.findByName")
                .setParameter("name", name)
                .getResultList();

        if (accounts != null && !accounts.isEmpty()) {
            return accounts.get(0);
        }

        return null;
    }

    public Journal findJournal(Object code) {
        List<Journal> journals = em.createNamedQuery("Journal.findByCode")
                .setParameter("code", code)
                .getResultList();

        if (journals != null && !journals.isEmpty()) {
            return journals.get(0);
        }

        return null;
    }

    public List<SaleOrder> findDrafts() {
        List<SaleOrder> draftOrders = em.createNamedQuery("SaleOrder.findDrafts")
                .setParameter("draft", "Draft Quotation")
                .setParameter("cancelled", "Cancelled")
                .getResultList();

        return draftOrders;
    }

    public List<SaleOrder> findConfirmed() {
        List<SaleOrder> confirmedOrders = em.createNamedQuery("SaleOrder.findConfirmed")
                .setParameter("draft", "Draft Quotation")
                .setParameter("cancelled", "Cancelled")
                .getResultList();

        return confirmedOrders;
    }

    public List<SaleOrder> findByPartner(Integer partnerId) {
        List<SaleOrder> OrdersByPartner = em.createNamedQuery("SaleOrder.findByPartner")
                .setParameter("partnerId", partnerId)
                .getResultList();

        return OrdersByPartner;
    }

    public List<SaleOrderLine> findByProduct(Integer productId) {
        List<SaleOrderLine> OrderLinesByProduct = em.createNamedQuery("SaleOrderLine.findByProduct")
                .setParameter("productId", productId)
                .getResultList();

        return OrderLinesByProduct;
    }

    public List<Partner> findTopNCustomers(int n) {
        List result = em.createNamedQuery("Partner.findByCustomer")
                .setMaxResults(n)
                .getResultList();

        return result;
    }

    public List<Product> findTopNSoldProducts(int n) {
        List result = em.createNamedQuery("Product.findBySaleOk")
                .setMaxResults(n)
                .getResultList();

        return result;
    }

    public List<SaleOrder> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(SaleOrder.class));
        return em.createQuery(cq).getResultList();
    }

}

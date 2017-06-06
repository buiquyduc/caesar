/**
 * 
 */
package com.casa.erp.dao.base;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.casa.erp.globals.GlobalConstants;

/**
 * @author ducbq
 *
 */
public abstract class BaseDAO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 163419734110707069L;
	@PersistenceContext(unitName = GlobalConstants.APPLICATION_PERSISTENCE_UNIT)
    protected EntityManager em;
}

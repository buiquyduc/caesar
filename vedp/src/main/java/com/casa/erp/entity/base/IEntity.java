/**
 * 
 */
package com.casa.erp.entity.base;

import java.io.Serializable;

/**
 * @author ducbq
 *
 */
public interface IEntity extends Serializable{
	Long getId();
	void setId(Long id);
}

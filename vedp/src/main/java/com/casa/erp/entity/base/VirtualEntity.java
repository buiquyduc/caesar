/**
 * 
 */
package com.casa.erp.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * @author ducbq
 *
 */
@MappedSuperclass
public class VirtualEntity implements IEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5534945729717571460L;

	@Id
	@SequenceGenerator(initialValue = 1, name = "idgen")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "idgen")
	@Column(name = "id")
	protected Long id;

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}

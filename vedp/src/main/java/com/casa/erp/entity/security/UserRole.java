package com.casa.erp.entity.security;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.casa.erp.entities.User;
import com.casa.erp.entity.base.VirtualEntity;

/**
 * 
 * @author ducbq
 */
@Entity
@Table(name = "user_role")
public class UserRole extends VirtualEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5738835622345821174L;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role role;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}

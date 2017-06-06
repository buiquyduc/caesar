/**
 * 
 */
package net.vedp.services;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.vedp.entities.User;

/**
 * @author ducbq
 *
 */
@Named
@SessionScoped
public class CeberusIdentity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8484199701757379281L;
	@Inject
	@Named("loggedUser")
	private User loggedUser;

	public boolean hasPermission(Object target, String action){
		System.out.println("Target: " + target + ". Action: " + action + "User: " + this.loggedUser.getLogin());
		return true;
	}
}

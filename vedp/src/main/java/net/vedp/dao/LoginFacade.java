
package net.vedp.dao;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import net.vedp.dao.base.BaseDAO;
import net.vedp.entities.User;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */


@Stateless
public class LoginFacade extends BaseDAO{
    public User userExist(String username, String password) {

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.login = :login AND u.password = :password AND u.active = 1", User.class);

        query.setParameter("login", username);
        query.setParameter("password", password);
        try {
            User user = query.getSingleResult();
            System.out.println("User exists");
            return user;
        } catch (NoResultException e) {
            System.out.println("User doesn't exist");
            return null;
        }
    }


}

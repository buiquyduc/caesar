package com.casa.erp.beans;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.casa.erp.beans.util.JsfUtil;
import com.casa.erp.dao.LoginFacade;
import com.casa.erp.entities.User;

/**
 * 
 * @author MOHAMMED BOUNAGA
 * 
 * github.com/medbounaga
 */

@Named(value = "userSessionController")
@SessionScoped
public class UserSessionController implements Serializable {

    @Inject
    private LoginFacade loginFacade;

    private static final long serialVersionUID = 7765876811740798583L;
    private User user;
    private String username;
    private String password;
    private boolean loggedIn;

	@Produces
	@SessionScoped
	@Named("loggedUser")
	private User loggedUser = new User();    
    
	public String doLogin() {

        if (username == null || password == null) {
            JsfUtil.addErrorMessage("InvalidLogin");
            return "/pages/loginPage.xhtml";

        } 
        
        user = loginFacade.userExist(username, password);
        
        if (user != null) {           
            loggedIn = true;
            loggedUser = user;
            return "/pages/dashboard.xhtml?faces-redirect=true";

        } 
            
        JsfUtil.addErrorMessage("InvalidLogin");
        return "/pages/loginPage.xhtml";

    }


    public String doLogout() {
        user = null;
        loggedIn = false;
        return "/pages/loginPage.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        if (loggedIn == false) {
            System.out.println("FacesContext instance is null: " + (FacesContext.getCurrentInstance() == null));
            if (FacesContext.getCurrentInstance() != null) {
                HttpServletRequest requestObj = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                requestObj.getSession().invalidate();
            }

        }

        System.out.println("LogedIn: " + loggedIn);
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

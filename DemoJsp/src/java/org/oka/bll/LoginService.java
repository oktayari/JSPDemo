package org.oka.bll;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.oka.model.Rolest;
import org.oka.model.Userst;
import org.oka.util.Helpers;
import org.oka.validators.Validator;


@Named
@SessionScoped
public class LoginService implements Serializable{

    @Inject UserService userService;

    public LoginService() {
    }

    public boolean authenticate(HttpServletRequest request) {

        String userName = request.getParameter("userName").toString();
        String passWord = request.getParameter("passWord").toString();
       
        String encriptedPassword = Helpers.encrypt(passWord);

        if (!userName.trim().isEmpty()) {
            Userst user = userService.getUser(userName);

            if (user != null) {
                if (user.getPassWord().equalsIgnoreCase(passWord)) {
                    request.getSession().putValue("loggedInUser", user);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean authenticate(String userName, String passWord) {

        if (!userName.trim().isEmpty()) {
            Userst user = userService.getUser(userName);

            if (user != null) {
                return user.getPassWord().equalsIgnoreCase(passWord);
            }
        }
        return false;
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

    public void showInfo(HttpServletRequest request) {
        String userName = request.getParameter("userName");
        Userst user = userService.getUser(userName);
        request.setAttribute("myInfo", user);

    }

    public String register(HttpServletRequest request) {
        String urlx = "";
        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String passWord = request.getParameter("passWord");
        String email = request.getParameter("email");
        

        Userst user = new Userst();
        user.setUserName(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassWord(passWord);
        user.setEmail(email);
        
        Validator validator = new Validator();
        boolean validationErrorFlag
                = validator.validateKullanici(
                        userName, firstName, lastName,
                        passWord, email, request);

        if (validationErrorFlag == true) {
            request.setAttribute("user", user);
            request.setAttribute("validationErrorFlag", validationErrorFlag);
            urlx = "/ortak/register.jsp";
        } else {
            userService.addUser(user);
            urlx = "/ortak/login.jsp";
        }

        return urlx;
    }
    
    public boolean isUserInRole(String roleName, Userst user) {
        boolean result = false;
        for (Rolest role : user.getRolestCollection()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return true;
            }
        }
        return result;
    }
    

}

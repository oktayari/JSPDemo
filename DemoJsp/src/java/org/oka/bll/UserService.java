package org.oka.bll;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import org.oka.dao.IRoleDao;
import org.oka.dao.IUserDao;
import org.oka.interceptors.LoggedInterceptor;
import org.oka.model.Rolest;
import org.oka.model.Userst;

@Interceptors(LoggedInterceptor.class)
@Named
@SessionScoped
public class UserService implements Serializable {

    @Inject
    IUserDao userDao;
    
    @Inject
    IRoleDao roleDao;

    public UserService() {
    }

    public List<Userst> getAllUsers() {
        return userDao.getAllUsers();
    }

    public List<Userst> getUsersByRange(int start, int end) {
        int[] range = new int[]{start, end};
        return userDao.findRange(range);
    }

    public Userst getUser(String userName) {
        return userDao.getUser(userName);
    }

    public void deleteUser(String userName) {
        userDao.deleteUser(userName);
    }

    public void editUser(Userst user) {
        userDao.editUser(user);
    }

    public void addUser(Userst user) {
        userDao.addUser(user);
    }

    public List<Rolest> getAllRoles() {
        return roleDao.getAllRoles();
    }

    public Rolest getRoleById(String roleName) {
        return roleDao.getRole(roleName);
    }

    public List<String> getUserRoles(Userst user) {
        Rolest[] userRolesa = user.getRolestCollection().toArray(new Rolest[user.getRolestCollection().size()]);
        List<String> userRoles = new ArrayList<>();
        for (Rolest item : userRolesa) {
            userRoles.add(item.getRoleName());
        }
        return userRoles;
    }

    public List<Rolest> getUserRolesb(Userst user) {
        Rolest[] userRolesa = user.getRolestCollection().toArray(new Rolest[user.getRolestCollection().size()]);
        List<Rolest> userRoles = new ArrayList<>();
        userRoles.addAll(Arrays.asList(userRolesa));
        return userRoles;
    }

    public void setUserRoles(String[] roles, Userst user) {

        if (roles != null && roles.length > 0) {
            for (String item : roles) {
                Rolest role = getRoleById(item);
                user.getRolestCollection().add(role);
            }
        }
    }

}

package org.oka.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import org.oka.model.Userst;

@Named
@ApplicationScoped
@Alternative
//@Default
public class UserDaoMock implements IUserDao, Serializable {

    static HashMap<String, Userst> userList = new HashMap<>();

    RoleDaoMock roleDao;

    public UserDaoMock() {
        roleDao = new RoleDaoMock();
        setUserList();
    }

    private void setUserList() {

     
        Userst user = new Userst();
        user.setUserName("oktaya");
        user.setFirstName("oktay");
        user.setLastName("Ari");
        user.setPassWord("1234");
        user.setEmail("oktaya@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("admin"));
        userList.put(user.getUserName(), user);

        user = new Userst();
        user.setUserName("elifa");
        user.setFirstName("elif");
        user.setLastName("Ari");
        user.setPassWord("1234");
        user.setEmail("elifa@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("user"));
        userList.put(user.getUserName(), user);

        user = new Userst();
        user.setUserName("sukrana");
        user.setFirstName("Sukran");
        user.setLastName("Ari");
        user.setPassWord("1234");
        user.setEmail("sukrana@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("user"));
        userList.put(user.getUserName(), user);

        user = new Userst();
        user.setUserName("aliv");
        user.setFirstName("Ali");
        user.setLastName("Veli");
        user.setPassWord("1234");
        user.setEmail("aliv@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("user"));
        userList.put(user.getUserName(), user);

        user = new Userst();
        user.setUserName("lalej");
        user.setFirstName("Lale");
        user.setLastName("Jale");
        user.setPassWord("1234");
        user.setEmail("lalej@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("user"));
        userList.put(user.getUserName(), user);

        user = new Userst();
        user.setUserName("ahmetd");
        user.setFirstName("Ahmet");
        user.setLastName("dede");
        user.setPassWord("1234");
        user.setEmail("ahmetd@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("user"));
        userList.put(user.getUserName(), user);

         user = new Userst();
        user.setUserName("admin");
        user.setFirstName("admin");
        user.setLastName("tonetici");
        user.setPassWord("admin");
        user.setEmail("admin@yahoo.com");
        user.setCreationDate(new Date());
        user.getRolestCollection().add(roleDao.getRole("admin"));
        user.getRolestCollection().add(roleDao.getRole("user"));
        userList.put(user.getUserName(), user);
        
    }

    @Override
    public void addUser(Userst user) {
        user.setCreationDate(new Date());
        userList.put(user.getUserName(), user);
    }

    @Override
    public void editUser(Userst user) {
        user.setCreationDate(userList.get(user.getUserName()).getCreationDate());
        deleteUser(user.getUserName());
        userList.put(user.getUserName(), user);

    }

    @Override
    public void deleteUser(String userName) {
        userList.remove(userName);
    }

    @Override
    public Userst getUser(String userName) {
        Userst user = (Userst) userList.get(userName);
        return user;
    }

    @Override
    public List<Userst> getAllUsers() {

        List<Userst> users = new ArrayList<>(userList.values());

        return users;
    }

    @Override
    public int count() {
        return userList.size();
    }

    @Override
    public List<Userst> findRange(int[] range) {
        List<Userst> users = new ArrayList<>(userList.values());

        List<Userst> result = new ArrayList<>();

        int start = range[0];
        int end = range[1];
        for (int i = start; i < end; i++) {

            if (i < users.size()) {
                result.add(users.get(i));
            }
        }

        return result;
    }

}

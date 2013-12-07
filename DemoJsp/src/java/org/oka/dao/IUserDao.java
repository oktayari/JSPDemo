
package org.oka.dao;

import java.util.List;
import org.oka.model.Userst;


public interface IUserDao {
     
     void addUser(Userst user);
     void editUser(Userst user);
     void deleteUser(String userName);
     Userst getUser(String userName);
     List<Userst> getAllUsers();
     int count();
     List<Userst> findRange(int[] range);
    
}


package org.oka.dao;

import java.util.List;
import org.oka.model.Rolest;

public interface IRoleDao {
    
     void addRole(Rolest role);
     void editRole(Rolest role);
     void deleteRole(String roleName);
     Rolest getRole(String roleName);
     List<Rolest> getAllRoles();
     int count();
     List<Rolest> findRange(int[] range);
}

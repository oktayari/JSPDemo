
package org.oka.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import org.oka.model.Rolest;

@Named
@ApplicationScoped
@Alternative 
//@Default
public class RoleDaoMock  implements IRoleDao{

    static HashMap<String, Rolest> rolesList = new HashMap<>();
    
    
    public RoleDaoMock()
    {
         setRolesList();
    }
     
    private void setRolesList() {
        Rolest role = new Rolest();
        role.setRoleName("admin");

        rolesList.put(role.getRoleName(), role);

        role = new Rolest();
        role.setRoleName("user");

        rolesList.put(role.getRoleName(), role);

    }
    
    @Override
    public void addRole(Rolest role) {
        rolesList.put(role.getRoleName(), role);
    }

    @Override
    public void editRole(Rolest role) {
      deleteRole(role.getRoleName());
      rolesList.put(role.getRoleName(), role);
    }

    @Override
    public void deleteRole(String roleName) {
         rolesList.remove(roleName);
    }

    @Override
    public Rolest getRole(String roleName) {
        return rolesList.get(roleName);
    }

    @Override
    public List<Rolest> getAllRoles() {
         List<Rolest> roles = new ArrayList<>(rolesList.values());
        
        return roles;
    }

    @Override
    public int count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rolest> findRange(int[] range) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

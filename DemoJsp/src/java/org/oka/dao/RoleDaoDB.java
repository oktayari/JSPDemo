package org.oka.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Named;
import org.oka.model.Rolest;
import org.oka.util.DbUtil;

@Named
@SessionScoped
@Alternative
//@Default
public class RoleDaoDB implements IRoleDao,Serializable {

    private final Connection connection;

    public RoleDaoDB() {
        connection = DbUtil.getConnection();

    }

    @Override
    public void addRole(Rolest role) {
        
        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("insert into Rolest(roleName) values (?)");
            // Parameters start with 1
            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void editRole(Rolest role) {
       
         try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("update Rolest set "
                            + "roleName=? where roleName=?");
            // Parameters start with 1

            preparedStatement.setString(1, role.getRoleName());
            preparedStatement.setString(2, role.getRoleName());
           
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteRole(String roleName) {
        
        
        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("delete from Rolest where roleName=?");
            // Parameters start with 1
            preparedStatement.setString(1, roleName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    @Override
    public Rolest getRole(String roleName) {
        
        Rolest role = null;
        try {
            PreparedStatement preparedStatement
                    = connection.
                    prepareStatement("select * from Rolest where roleName=?");
            preparedStatement.setString(1, roleName);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    role = new Rolest();
                    role.setRoleName(rs.getString("roleName"));
                   
                }
            }
        } catch (SQLException e) {
        }

        return role;
    }

    @Override
    public List<Rolest> getAllRoles() {
        List<Rolest> result = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Rolest");
            while (rs.next()) {
                Rolest role = new Rolest();
                role.setRoleName(rs.getString("roleName"));
                result.add(role);

            }
        } catch (SQLException e) {
        }
        return result;
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

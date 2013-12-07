
package org.oka.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.oka.model.UserRoles;
import org.oka.util.DbUtil;


public class UserRolesDaoDB {
    
    
    private final Connection connection;

    public UserRolesDaoDB() {
        connection = DbUtil.getConnection();

    }
    
    public void add(UserRoles userRole)
    {
        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("insert into userroles(userName,roleName) values (?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, userRole.getUserName());
             preparedStatement.setString(2, userRole.getRoleName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    public void delete(UserRoles userRole)
    {
        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("delete from userroles where roleName=? and userName=?");
            // Parameters start with 1
            preparedStatement.setString(1, userRole.getRoleName());
              preparedStatement.setString(1, userRole.getUserName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
        
    }
    
    public List<UserRoles> getListByUserName(String username)
    {
         List<UserRoles> result = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from userroles where userName='" +username+ "'");
            
            while (rs.next()) {
                UserRoles userRole = new UserRoles();
                userRole.setRoleName(rs.getString("roleName"));
                userRole.setUserName(rs.getString("userName"));
                result.add(userRole);

            }
        } catch (SQLException e) {
        }
        return result;
       
    }
    
    public void deleteByUserName(String userName)
    {
        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("delete from userRoles where userName=?");
            // Parameters start with 1
            preparedStatement.setString(1, userName);
            
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
}

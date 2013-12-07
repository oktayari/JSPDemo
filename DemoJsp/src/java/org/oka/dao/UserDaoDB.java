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
import org.oka.model.UserRoles;
import org.oka.model.Userst;
import org.oka.util.DbUtil;

@Named
@SessionScoped
@Alternative
//@Default
public class UserDaoDB implements IUserDao, Serializable {

    private final Connection connection;

    UserRolesDaoDB userRoleDao;
    RoleDaoDB roleDao;

    public UserDaoDB() {
        connection = DbUtil.getConnection();
        userRoleDao = new UserRolesDaoDB();
        roleDao = new RoleDaoDB();

    }

    @Override
    public void addUser(Userst user) {

        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("insert into Userst(userName,firstName,lastName,"
                            + "passWord,email) values (?, ?, ?, ? ,?)");
            // Parameters start with 1
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getPassWord());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.executeUpdate();

            addUserRoles(user);
        } catch (SQLException e) {
        }
    }

    @Override
    public void editUser(Userst user) {

        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("update Userst set "
                            + "firstName=?, lastName=?, passWord=? ,email=? "
                            + " where userName=?");
            // Parameters start with 1

            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassWord());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(6, user.getUserName());
            preparedStatement.executeUpdate();

            deleteUserRoles(user.getUserName());
            addUserRoles(user);
        } catch (SQLException e) {
        }
    }

    @Override
    public void deleteUser(String userName) {

        try {
            PreparedStatement preparedStatement
                    = connection
                    .prepareStatement("delete from Userst where userName=?");
            // Parameters start with 1
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();

            deleteUserRoles(userName);

        } catch (SQLException e) {
        }
    }

    @Override
    public Userst getUser(String userName) {
        Userst user = null;
        try {
            PreparedStatement preparedStatement
                    = connection.
                    prepareStatement("select * from Userst where userName=?");
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null) {
                if (rs.next()) {
                    user = new Userst();
                    user.setUserName(userName);
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setPassWord(rs.getString("passWord"));
                    user.setEmail(rs.getString("email"));
                    user.setCreationDate(rs.getDate("creationDate"));

                    setUserRoles(user);
                }
            }
        } catch (SQLException e) {
        }

        return user;
    }

    @Override
    public List<Userst> getAllUsers() {

        List<Userst> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Userst");
            while (rs.next()) {
                Userst user = new Userst();
                user.setUserName(rs.getString("userName"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setPassWord(rs.getString("passWord"));
                user.setEmail(rs.getString("email"));
                user.setCreationDate(rs.getDate("creationDate"));
                setUserRoles(user);
                userList.add(user);
            }
        } catch (SQLException e) {
        }

        return userList;
    }

    @Override
    public int count() {
        return getAllUsers().size();
    }

    @Override
    public List<Userst> findRange(int[] range) {

        List<Userst> users = getAllUsers();

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

    private void addUserRoles(Userst user) {
        for (Rolest role : user.getRolestCollection()) {
            UserRoles userRole = new UserRoles();
            userRole.setRoleName(role.getRoleName());
            userRole.setUserName(user.getUserName());
            userRoleDao.add(userRole);
        }

    }

  
    private void deleteUserRoles(String userName) {
        userRoleDao.deleteByUserName(userName);

    }

    private void setUserRoles(Userst user) {

        List<UserRoles> userroles = userRoleDao.getListByUserName(user.getUserName());

        for (UserRoles userRole : userroles) {
            Rolest role = roleDao.getRole(userRole.getRoleName());
            user.getRolestCollection().add(role);
        }

    }

}

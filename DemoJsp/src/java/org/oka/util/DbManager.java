package org.oka.util;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public final class DbManager {

      InitialContext _ctx = null;
    DataSource _ds = null;
    Connection _con = null;
    
    boolean connected = false;

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }
 

    public DbManager() throws NamingException {

        connected = Connect();
    }

    boolean Connect() throws NamingException {

        try {
            _ctx = new InitialContext();
            _ds = (DataSource) _ctx.lookup("mySql");
            //ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mySql");
            _con = _ds.getConnection();
            //_con = DriverManager.getConnection(_connectionString, _dbUser, _dbPass);
            return true;
        } catch (SQLException ex) {
        }

        return false;
    }

   public  boolean ExecuteUpdateInsertDelete(String query) {
        boolean result = false;
           try {
             Statement st  = _con.createStatement();
            int asd = st.executeUpdate(query);
            if (asd > 0) {
                result = true;
            }

            _con.close();
        } catch (SQLException ex) {
        }

        return result;
    }

   public  ResultSet GetResultSet(String query) {
       ResultSet rs = null;

        try {
            Statement st = _con.createStatement();
            rs = st.executeQuery(query);
            _con.close();
        } catch (SQLException ex) { }

        return rs;

    }

}

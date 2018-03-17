/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * Database connection class
 * @author 101794
 */
public class DBConnect {
    //class variables
    //initialize database CONSTANTS
    private static final String DATABASE = "mysql";
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_HOST = "localhost";
    private static final String DATABASE_PORT = "3306";
    private static final String DATABASE_NAME = "ooproject";
    private static final String DATABASE_URL = "jdbc:" + DATABASE + "://" + DATABASE_HOST + ":" + DATABASE_PORT + "/" + DATABASE_NAME;
    private static final String USERNAME = "anoop_user";
    private static final String PASSWORD = "0OP@ssword!";
    //database table name CONSTANTS
    public static final String COUNTIES_TABLE = "counties";
    public static final String USERS_TABLE = "users";
    
    //instance variables
    private static Connection connection;
    private Properties properties;
    
    //create the properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
        }
        return properties;
    }
    
    //connect to the database
    public Connection connect() {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
                System.out.println("Successfully connected to the database");
            } catch (ClassNotFoundException | SQLException err) {
                //handle errors
                System.out.println("SQLException: " + err.getMessage());
                err.printStackTrace();
                //@TODO: Show visual notification of error and message e.g. in coloured circle or status bar
            }
        }
        return connection;
    }
    
    //disconnect the database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException err) {
                err.printStackTrace();
            }
        }
    }
    
    //close a statement
    public Statement close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return stmt;
    }
    
    //close a prepared statement
    public PreparedStatement close(PreparedStatement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
                stmt = null;
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return stmt;
    }
    
    //get a resultset from the DB
    public ResultSet getResult(String sql) {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //execute the sql statement
            if (stmt.execute(sql)) {
                rs = stmt.getResultSet();
                if(rs.isBeforeFirst()) return rs; //return the resultset if rows > 0
            }
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Oops! Error retrieving result. Resultset may be empty", "DB Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    //execute a sql query
    public void execute(String sql) throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {}
        stmt.close();
    }
    
      //execute a prepared sql query
    public void execute(PreparedStatement stmt) throws SQLException {
        try {
            stmt.executeUpdate();
        } catch (SQLException e) {}
        stmt.close();
    }
    
    //commits updates to the database
    public void commit() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {}
    }
    
//    public static void main(String[] args) {
//        DBConnect db = new DBConnect();
//        db.connect();
//    }
}

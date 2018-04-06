/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User Database Table Model
 * 
 * @author 101794
 */
public class UserModel {
    private String username, fullname, email, county, pass;
    private int id, role;
    
    //create an empty 'placeholder' user model
    UserModel() {
        
    }
    
    UserModel(String u, String f, String e, String c, int r, int i) {
        username = u;
        fullname = f;
        email = e;
        county = c;
        role = r;
        id = i;
    }
    //getters
    public String getUsername() {
        return username;
    }
    public String getFullname() {
        return fullname;
    }
    public String getEmail() {
        return email;
    }
    public String getCounty() {
        return county;
    }
    public int getCountyCode() {
        try {
            ResultSet rs = Utils.db().getResult("SELECT `code` FROM `" + DB.COUNTIES_TABLE + "`"
                    + " WHERE `name` = '" + this.county + "'");
            if (rs.next()) {
                return rs.getInt("code");
            }
        } catch (Exception e) {
            Utils.showDialog("Error getting countID " +e.getMessage(), "Error");
        }
        return 0;
    }
    //
    public int getRole(){
        return role;
    }
    
    public int getID(){
        return id;
    }
    
    public static boolean exists(String username) {
        boolean exists = false;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM `"+ DB.USERS_TABLE + "` "
                    + "WHERE `username` = ?";
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //username exists
                exists = true;
            }
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        } finally {
            Utils.db().close(stmt);
        }
        
        return exists;
    }
    
    public static boolean add(String[] user) {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DB.USERS_TABLE + "` "
                    + "(`fullname`, `username`, `email`, `password`, `county`)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            for (int i=0; i < 5; i++) {
                stmt.setString(i+1, user[i]);
            }
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return status;
    }
    
    public boolean update() {
        boolean status = false;
        try {
            String sql = "UPDATE `"+ DB.USERS_TABLE + "` SET "
                    + "`fullname` = ?, `username` = ?, `email` = ?, `password` = ?, `county` = ?, `role` = ?"
                    + " WHERE `id`= "+ id;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, fullname);
            stmt.setString(2, username);
            stmt.setString(3, email);
            stmt.setString(4, pass);//??
            stmt.setString(5, county);
            stmt.setInt(6, role);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }       
        return status;
    }
    
    public boolean delete() {
        boolean status = false;
        try {
            String sql = "DELETE FROM `"+ DB.USERS_TABLE + "` "
                    + "WHERE `id` = ?";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }      
        
        return status;
    }
    
    public UserModel find(int id) {
        UserModel model=null;
        String sql = "SELECT * FROM " + DB.USERS_TABLE + " "
                + "WHERE `id` = ?";
        try {
            ResultSet rs = null;
            PreparedStatement stmt = null;
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //county exists
                model = new UserModel(rs.getString("username"), rs.getString("fullname"), rs.getString("email"), rs.getString("county"), rs.getInt("role"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            Utils.showDialog("Error finding user " + e.getMessage(), "Error");
        }
        
        return model;
    }
}

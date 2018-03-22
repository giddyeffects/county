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
    public int getCountyID() {
        try {
            ResultSet rs = Utils.db().getResult("SELECT `id` FROM `" + DBConnect.COUNTIES_TABLE + "`"
                    + " WHERE `name` = " + this.county);
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (Exception e) {
        }
        return 0;
    }
    //two roles for now. System ADmin and normal user
    public String getRole(){
        return (role == 5)?"System Administrator":"Normal User";
    }
    
    public int getID(){
        return id;
    }
    
    public static boolean exists(String username) {
        boolean exists = false;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM `"+ DBConnect.USERS_TABLE + "` "
                    + "WHERE `username` = ?";
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //username exists
                exists = true;
            }
        } catch (SQLException e) {
            
        } finally {
            Utils.db().close(stmt);
        }
        
        return exists;
    }
    
    public static boolean add(String[] user) {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DBConnect.USERS_TABLE + "` "
                    + "(`fullname`, `username`, `email`, `password`, `county`)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            for (int i=0; i < 5; i++) {
                stmt.setString(i+1, user[i]);
            }
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return status;
    }
    
    public static boolean update(UserModel user) {
        boolean status = false;
        try {
            String sql = "UPDATE `"+ DBConnect.USERS_TABLE + "` SET "
                    + "`fullname` = ?, `username` = ?, `email` = ?, `password` = ?, `county` = ?, `role` = ?"
                    + " WHERE `id`= "+ user.id;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, user.fullname);
            stmt.setString(2, user.username);
            stmt.setString(3, user.email);
            stmt.setString(4, user.pass);//??
            stmt.setString(5, user.county);
            stmt.setInt(6, user.role);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return status;
    }
    
    public static boolean delete(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM `"+ DBConnect.USERS_TABLE + "` "
                    + "WHERE `id` = ?";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            
        }      
        
        return status;
    }
}

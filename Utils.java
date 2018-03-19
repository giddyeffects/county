/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.swing.JOptionPane;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 * Utility functions to be used across various classes in the project
 * @author 101794
 */
public class Utils {
    
    //global variables
    public static boolean loggedIn = false;
    public static User user;
    
    public static boolean checkUsername(String s) {
        String regex = "^[a-zA-Z0-9]+$"; //allow alphanumeric only for the username
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).matches();
    }
    
    public static boolean userExists(String username) {
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
    
    public static boolean emailValid(String email) {
        boolean isValid = false;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            
        }
        return isValid;
    }
    
    public static String encrypt(String plainPass) {
        return new BasicPasswordEncryptor().encryptPassword(plainPass);
    }
    
    public static boolean checkPassword(String pass, String encryptedPass) {
        return new BasicPasswordEncryptor().checkPassword(pass, encryptedPass);
    }

    public static boolean saveUser(String[] user) {
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
    
    public static boolean login(String[] user) {
        boolean status = false;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM `"+ DBConnect.USERS_TABLE + "` "
                    + "WHERE `username` = ?";
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, user[0]);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //username found... now compare given password with stored encrypted password
                if(checkPassword(user[1],rs.getString("password"))) {
                    //user authenticated... create an user Object and proceed to open main app
                    Utils.loggedIn = true;
                    Utils.user = new User(rs.getString("username"), rs.getString("fullname"), rs.getString("email"), rs.getString("county"), rs.getInt("role"), rs.getInt("id") );
                    showDialog("User "+user[0]+" Logged in. You can proceed","Info");
                    status = true;
                } else {
                    showDialog("Incorrect password","Alert");
                }
            } else {
                showDialog("User "+user[0]+" not found","Alert");
            }
        } catch (SQLException e) {
            
        } finally {
            Utils.db().close(stmt);
        }
        return status;
    }
    
    public static void logout() {
        //set loggedIn to false
        Utils.loggedIn = false;
        //clear user Object
        Utils.user = null;
    }

    public static DBConnect db() {
        return new DBConnect();
    }
    
    public static JComboBox getCounties(JComboBox dd) {
        try {
            ResultSet rs = Utils.db().getResult("SELECT name FROM "+DBConnect.COUNTIES_TABLE);
            while(rs.next()) {
                //System.out.println(rs.getString("name"));
                dd.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            
        }
        return dd;
    }
    
    public static void showDialog(String msg, String title) {
        if(null != title)switch (title) {
            case "Info":
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
                break;
            case "Alert":
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.WARNING_MESSAGE);
                break;
            case "Error":
                JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
                break;
            default:
                break;
        }
    }
    
    public static int showDialog(String msg) {
        return JOptionPane.showConfirmDialog(null, msg);
    }
    
}

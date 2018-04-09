/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.Timer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 * Utility functions to be used across various classes in the project
 * @author 101794
 */
public class Utils {
    
    //global variables
    public static boolean loggedIn = false;
    public static UserModel user;
    
    public static boolean checkUsername(String s) {
        String regex = "^[a-zA-Z0-9]+$"; //allow alphanumeric only for the username
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(s).matches();
    }
    
    public static boolean emailValid(String email) {
        boolean isValid = false;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            isValid = true;
        } catch (AddressException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return isValid;
    }
    
    public static String encrypt(String plainPass) {
        return new BasicPasswordEncryptor().encryptPassword(plainPass);
    }
    
    public static boolean checkPassword(String pass, String encryptedPass) {
        return new BasicPasswordEncryptor().checkPassword(pass, encryptedPass);
    }
    
    public static boolean login(String[] user) {
        boolean status = false;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        try {
            String sql = "SELECT * FROM `"+ DB.USERS_TABLE + "` "
                    + "WHERE `username` = ?";
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, user[0]);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //username found... now compare given password with stored encrypted password
                if(checkPassword(user[1],rs.getString("password"))) {
                    //user authenticated... create an user Object and proceed to open main app
                    Utils.loggedIn = true;
                    Utils.user = new UserModel(rs.getString("username"), rs.getString("fullname"), rs.getString("email"), rs.getString("county"), rs.getInt("role"), rs.getInt("id") );
                    showDialog("User "+user[0]+" Logged in. You can proceed","Info");
                    status = true;
                } else {
                    showDialog("Incorrect password","Alert");
                }
            } else {
                showDialog("User "+user[0]+" not found","Alert");
            }
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
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
        //disconnect the database connection
        Utils.db().disconnect();
    }

    public static DB db() {
        return new DB();
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
    
    public static String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("Africa/Nairobi"));
        return df.format(new Date());
    }
    /**
     * Show status message text in JLabel label for sec seconds
     * @param label
     * @param text
     * @param secs 
     * @param status
     */
    public static void showStatus(JLabel label, String text, int secs, String status) {
        label.setText(text);
        switch (status) {
            case "success":
                label.setForeground(Color.GREEN);
                break;
            case "error":
                label.setForeground(Color.RED);
                break;
            default:
                label.setForeground(Color.BLUE);
                break;
        }
        Timer timer = new Timer(secs * 1000, (ActionEvent e) -> {
            label.setText("");
        });
        timer.start();
    }
    
    public static JTextField setRowSorter(JTable table, JTextField filterTxt) {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        filterTxt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = filterTxt.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));//case insensitive rowfilter
                }
            }
        });
        
        return filterTxt;
    }
    
}

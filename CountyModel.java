/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author 101794
 */
public class CountyModel {
    private final int code, population;
    private final String name, province, capital;
    private final double area;
    
    CountyModel(int c, String n, String prov, double a, int pop, String cap) {
      code = c;
      name = n;
      province = prov;
      area = a;
      population = pop;
      capital = cap;
    }

    public static JComboBox getCountyNames(JComboBox dd) {
        try {
            ResultSet rs = Utils.db().getResult("SELECT name FROM " + DBConnect.COUNTIES_TABLE);
            while (rs.next()) {
                //System.out.println(rs.getString("name"));
                dd.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
        }
        return dd;
    }
    
    public static boolean add(CountyModel model) {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DBConnect.COUNTIES_TABLE +"` "
                    + "(`code`, `name`, `province`, `area`, `population`, `capital`)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, model.code);
            stmt.setInt(2, model.name);
            stmt.setString(3, model.province);
            stmt.setString(4, model.area);
            stmt.setString(5, model.population);
            stmt.setInt(6, model.capital);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
        }
        return status;
    }
    
    public static boolean update(CountyModel model) {
        boolean status = false;
        try {
            String sql = "UPDATE `"+ DBConnect.COUNTIES_TABLE +"` SET "
                    + "`name` = ?, `province` = ?, `area` = ?, `population` = ?, `capital` = ?"
                    + " WHERE `code` = "+ model.code;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, model.name);
            stmt.setInt(2, model.province);
            stmt.setString(3, model.area);
            stmt.setString(4, model.population);
            stmt.setString(5, model.capital);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
        }
        return status;
    }
    
    public static boolean delete(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM `"+ DBConnect.COUNTIES_TABLE + "` "
                    + "WHERE `code` = ?";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            
        }
        return status;
    }
    
    public static CountyModel find(int id) {
        CountyModel model=null;
        String sql = "SELECT * FROM " + DBConnect.COUNTIES_TABLE + " "
                + "WHERE `code` = ?";
        ResultSet rs = null;
        PreparedStatement stmt = null;
        
        return model;
    }
    
    //get counties list
    ArrayList<CountyModel> getCountiesList() {
        ArrayList<CountyModel> countiesList = new ArrayList<>();
        String sql = "SELECT * FROM " + DBConnect.COUNTIES_TABLE;
        
        
        return countiesList;
    }
    
}

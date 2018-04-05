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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 101794
 */
public class CountyModel implements Model<CountyModel> {
    private final int code, population;
    private final String name, province, capital, area;
    
    //create an empty county model 'placeholder' object
    CountyModel() {
        code = population = 0;
        name = province = capital = area = null;
    }
    
    CountyModel(int c, String n, String prov, String a, int pop, String cap) {
      code = c;
      name = n;
      province = prov;
      area = a;
      population = pop;
      capital = cap;
    }

    //model getters
    public int getCode(){
        return code;
    }
    public int getPopulation() {
        return population;
    }
    public String getName() {
        return name;
    }
    public String getProvince() {
        return province;
    }
    public String getCapital() {
        return capital;
    }
    public String getArea() {
        return area;
    }
    
    /**
     * get county names from the database
     * @param dd
     * @return 
     */
    public static JComboBox getCountyNames(JComboBox dd) {
        try {
            ResultSet rs = Utils.db().getResult("SELECT name FROM " + DB.COUNTIES_TABLE);
            while (rs.next()) {
                //System.out.println(rs.getString("name"));
                dd.addItem(rs.getString("name"));
            }
        } catch (Exception e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return dd;
    }
    
    /**
     * Adds a county to the database
     * @param model
     * @return boolean
     */
    @Override
    public boolean add(CountyModel model) {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DB.COUNTIES_TABLE +"` "
                    + "(`code`, `name`, `province`, `area`, `population`, `capital`)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, model.code);
            stmt.setString(2, model.name);
            stmt.setString(3, model.province);
            stmt.setString(4, model.area);
            stmt.setInt(5, model.population);
            stmt.setString(6, model.capital);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return status;
    }
    
    /**
     * Updates a county record
     * @param model
     * @return boolean
     */
    @Override
    public boolean update(CountyModel model) {
        boolean status = false;
        try {
            String sql = "UPDATE `"+ DB.COUNTIES_TABLE +"` SET "
                    + "`name` = ?, `province` = ?, `area` = ?, `population` = ?, `capital` = ?"
                    + " WHERE `code` = "+ model.code;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setString(1, model.name);
            stmt.setString(2, model.province);
            stmt.setString(3, model.area);
            stmt.setInt(4, model.population);
            stmt.setString(5, model.capital);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return status;
    }
    
    /**
     * Deletes a county
     * @param id
     * @return boolean
     */
    @Override
    public boolean delete(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM `"+ DB.COUNTIES_TABLE + "` "
                    + "WHERE `code` = ?";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog("Error deleting county "+e.getMessage(), "Error");
        }
        return status;
    }
    
    /**
     * Finds a county record
     * @param id
     * @return CountyModel
     */
    @Override
    public CountyModel find(int id) {
        CountyModel model=null;
        String sql = "SELECT * FROM " + DB.COUNTIES_TABLE + " "
                + "WHERE `code` = ?";
        try {
            ResultSet rs = null;
            PreparedStatement stmt = null;
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //county exists
                model = new CountyModel(rs.getInt("code"), rs.getString("name"), rs.getString("province"), rs.getString("area"), rs.getInt("population"), rs.getString("capital"));
            }
        } catch (SQLException e) {
            Utils.showDialog("Error finding county " + e.getMessage(), "Error");
        }
        
        return model;
    }
    
    //get counties list
    @Override
    public ArrayList<CountyModel> getList() {
        ArrayList<CountyModel> countiesList = new ArrayList<>();
        String sql = "SELECT * FROM " + DB.COUNTIES_TABLE;
        try {
            ResultSet rs = Utils.db().getResult(sql);
            CountyModel county;
            while (rs.next()) {
                county = new CountyModel(rs.getInt("code"), rs.getString("name"), rs.getString("province"), rs.getString("area"), rs.getInt("population"), rs.getString("capital"));//
                countiesList.add(county);
            }
        } catch (SQLException e) {
           Utils.showDialog(e.getMessage(), "Error");
        }        
        
        return countiesList;
    }
    
    public void showList(JTable table) {
        ArrayList<CountyModel> countiesList = getList();
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        Object[] row = new Object[6];
        for ( int i=0; i < countiesList.size(); i++) {
            row[0] = countiesList.get(i).getCode();
            row[1] = countiesList.get(i).getName();
            row[2] = countiesList.get(i).getProvince();
            row[3] = countiesList.get(i).getArea();
            row[4] = countiesList.get(i).getPopulation();
            row[5] = countiesList.get(i).getCapital();
            model.addRow(row);
        }
        
    }
}

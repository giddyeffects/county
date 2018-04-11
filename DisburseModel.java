/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class DisburseModel implements Model<DisburseModel> {
    private int id, project_id, disbursedby_id, amount;
    private Date disbursedon;
    
    //placeholder constructor
    DisburseModel() {
        
    }
    //create new disburse constructor
    DisburseModel(int pid, int did, int amt, Date don) {
        project_id = pid;
        disbursedby_id = did;
        amount = amt;
        disbursedon = don;
    }
    //arraylist constructor
    DisburseModel(int i, int pid, int did, int amt, Date don) {
        id = i;
        project_id = pid;
        disbursedby_id = did;
        amount = amt;
        disbursedon = don;        
    }
    //getters
    public int getID() {
        return id;
    }
    public int getProjectID() {
        return project_id;
    }
    public String getProjectTitle() {
        ProjectModel project = new ProjectModel();
        project.find(project_id);
        return project.getTitle();
    }
    public String getDisbursedBy() {
        UserModel user = new UserModel();
        user = user.find(disbursedby_id);
        return user.getFullname();
    }
    public int getAmount() {
        return amount;
    }
    public String getDisbursedOn() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setTimeZone(TimeZone.getTimeZone("Africa/Nairobi"));
        return df.format(disbursedon);
    }
    
    /**
     * Adds a disbursement entry to the database
     * @return boolean
     */
    @Override
    public boolean add() {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DB.DISBURSEMENTS_TABLE +"` "
                    + "(`project_id`, `disbursedby_id`, `amount`, `disbursedon`)"
                    + " VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, project_id);
            stmt.setInt(2, disbursedby_id);
            stmt.setInt(3, amount);
            stmt.setDate(4, new java.sql.Date(disbursedon.getTime()));
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return status;
    }
    
@Override
    public boolean update(int id) {
        boolean status = false;
        try {
            String sql = "UPDATE `"+ DB.DISBURSEMENTS_TABLE +"` SET "
                    + "`project_id` = ?, `disbursedby_id` = ?, `amount` = ?, `disbursedon` = ?"
                    + " WHERE `id` = "+ id;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            //NOTE: user_id is not updated
            stmt.setInt(1, project_id);
            stmt.setInt(2, disbursedby_id);
            stmt.setInt(3, amount);
            stmt.setDate(4, new java.sql.Date(disbursedon.getTime()));
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return status;
    }    
    
@Override
    public boolean delete(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM `"+ DB.DISBURSEMENTS_TABLE + "` "
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
    
    @Override
    public DisburseModel find(int id) {
        DisburseModel model=null;
        String sql = "SELECT * FROM " + DB.DISBURSEMENTS_TABLE
                + " WHERE `id` = ?";
        try {
            ResultSet rs = null;
            PreparedStatement stmt = null;
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //disbursement exists
                model = new DisburseModel(rs.getInt("id"), rs.getInt("project_id"), rs.getInt("disbursedby_id"), rs.getInt("amount"),rs.getDate("end_date"));
            }
        } catch (SQLException e) {
            Utils.showDialog("Error finding this fund allocation " + e.getMessage(), "Error");
        }
        
        return model;
    }
    
    //get disbursement list
    @Override
    public ArrayList<DisburseModel> getList() {
        ArrayList<DisburseModel> disburseList = new ArrayList<>();
        //show all disbursements...for transparency
        String sql = "SELECT * FROM " + DB.DISBURSEMENTS_TABLE;
        
        try {
            ResultSet rs = Utils.db().getResult(sql);
            DisburseModel disburse;
            if(rs != null) {
                while (rs.next()) {
                    disburse = new DisburseModel(rs.getInt("id"), rs.getInt("project_id"), rs.getInt("disbursedby_id"), rs.getInt("amount"),rs.getDate("end_date"));//
                    disburseList.add(disburse);
                }
            }
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        
        return disburseList;
    }
    
    @Override
    public void showList(JTable table) {
        ArrayList<DisburseModel> disburseList = getList();
        //if (disburseList.size() > 0) {
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setRowCount(0);
            Object[] row = new Object[5];
            for ( int i=0; i < disburseList.size(); i++) {
                row[0] = disburseList.get(i).getID();
                row[1] = disburseList.get(i).getProjectTitle();
                row[2] = disburseList.get(i).getAmount();
                row[3] = disburseList.get(i).getDisbursedBy();
                row[4] = disburseList.get(i).getDisbursedOn();
                model.addRow(row);
            }
        //}
    }
}

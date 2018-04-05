/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Project Database Table Model
 * 
 * @author 101794
 */
public class ProjectModel implements Model<ProjectModel> {
    private int id, county_id, user_id, activities, personnel, progress;
    private String title, sponsor, manager, budget, actual_cost;
    private Date start_date, end_date;
    
    //create empty project model 'placeholder' object
    ProjectModel() {
        
    }
    
    ProjectModel(int i, int c, int u, int a, int p, int pr, 
            String t, String s, String m, String b, String cost,
            Date start, Date end) {
        id = i;
        county_id = c;
        user_id = u;
        activities = a;
        personnel = p;
        progress = pr;
        title = t;
        sponsor = s;
        manager = m;
        budget = b;
        actual_cost = cost;
        start_date = start;
        end_date = end;
    }
    //getters
    public int getID() {
        return id;
    }
    public String getCounty() {
        CountyModel county = new CountyModel();
        county = county.find(county_id);
        return county.getName();
    }
    public String getUser() {
        UserModel user = new UserModel();
        user = user.find(user_id);
        return user.getFullname();
    }
    public int getActivities() {
        return activities;
    }
    public int getPersonnel() {
        return personnel;
    }
    public int getProgress() {
        return progress;
    }
    public String getTitle() {
        return title;
    }
    public String getSponsor() {
        return sponsor;
    }
    public String getManager() {
        return manager;
    }
    public String getBudget() {
        return budget;
    }
    public String getActualCost() {
        return actual_cost;
    }
    public Date getStartDate() {
        return start_date;
    }
    public Date getEndDate() {
        return end_date;
    }
    
    //get projects list
    @Override
    public ArrayList<ProjectModel> getList() {
        ArrayList<ProjectModel> projectsList = new ArrayList<>();
        String sql = "SELECT * FROM " + DB.PROJECTS_TABLE + " "
                + "WHERE user_id = "+ Utils.user.getID();
        try {
            ResultSet rs = Utils.db().getResult(sql);
            ProjectModel project;
            if(rs != null) {
                while (rs.next()) {
                    project = new ProjectModel(rs.getInt("id"), rs.getInt("county_id"), rs.getInt("user_id"), rs.getInt("activities"), rs.getInt("personnel"), 
                    rs.getInt("progress"), rs.getString("title"), rs.getString("sponsor"), rs.getString("manager"), rs.getString("budget"), rs.getString("actual_cost"), rs.getDate("start_date"),rs.getDate("end_date"));//
                    projectsList.add(project);
                }
            }
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        
        return projectsList;
    }
 
    public void showList(JTable table) {
        ArrayList<ProjectModel> projectList = getList();
        //if (projectList.size() > 0) {
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row = new Object[7];
            for ( int i=0; i < projectList.size(); i++) {
                row[0] = projectList.get(i).getTitle();
                row[1] = projectList.get(i).getSponsor();
                row[2] = projectList.get(i).getManager();
                row[3] = projectList.get(i).getBudget();
                row[4] = projectList.get(i).getStartDate();
                row[5] = projectList.get(i).getEndDate();
                row[6] = projectList.get(i).getProgress();
                model.addRow(row);
            }
        //}
        
    }
        
    /**
     * Adds a project to the database
     * @param model
     * @return boolean
     */
    @Override
    public boolean add(ProjectModel model) {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DB.PROJECTS_TABLE +"` "
                    + "(`user_id`, `county_id`, `title`, `sponsor`, `manager`, `activities`, `start_date`, `end_date`,"
                    + "`budget`, `actual_cost`,`personnel`,`progress`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, model.user_id);
            stmt.setInt(2, model.county_id);
            stmt.setString(3, model.title);
            stmt.setString(4, model.sponsor);
            stmt.setString(5, model.manager);
            stmt.setInt(6, model.activities);
            stmt.setDate(7, model.start_date);
            stmt.setDate(8, model.end_date);
            stmt.setString(9, model.budget);
            stmt.setString(10, model.actual_cost);
            stmt.setInt(11, model.personnel);
            stmt.setInt(12, model.progress);
            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
            Utils.showDialog(e.getMessage(), "Error");
        }
        return status;
    }
    
    @Override
    public boolean update(ProjectModel model) {
        boolean status = false;
        try {
            String sql = "UPDATE `"+ DB.PROJECTS_TABLE +"` SET "
                    + "`user_id` = ?, `county_id` = ?, `title` = ?, `sponsor` = ?, `manager` = ?, `activities` = ?,"
                    + " `start_date` = ?, `end_date` = ?,`budget` = ?, `actual_cost` = ?,`personnel` = ?,`progress` = ?"
                    + " WHERE `id` = "+ model.id;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, model.user_id);
            stmt.setInt(2, model.county_id);
            stmt.setString(3, model.title);
            stmt.setString(4, model.sponsor);
            stmt.setString(5, model.manager);
            stmt.setInt(6, model.activities);
            stmt.setDate(7, model.start_date);
            stmt.setDate(8, model.end_date);
            stmt.setString(9, model.budget);
            stmt.setString(10, model.actual_cost);
            stmt.setInt(11, model.personnel);
            stmt.setInt(12, model.progress);
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
            String sql = "DELETE FROM `"+ DB.PROJECTS_TABLE + "` "
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
    public ProjectModel find(int id) {
        ProjectModel model=null;
        String sql = "SELECT * FROM " + DB.PROJECTS_TABLE + " "
                + "WHERE `id` = ?";
        try {
            ResultSet rs = null;
            PreparedStatement stmt = null;
            stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()) {
                //project exists
                model = new ProjectModel(rs.getInt("id"), rs.getInt("county_id"), rs.getInt("user_id"), rs.getInt("activities"), rs.getInt("personnel"), 
                    rs.getInt("progress"), rs.getString("title"), rs.getString("sponsor"), rs.getString("manager"), rs.getString("budget"), rs.getString("actual_cost"), rs.getDate("start_date"),rs.getDate("end_date"));
                
            }
        } catch (SQLException e) {
            Utils.showDialog("Error finding project " + e.getMessage(), "Error");
        }
        
        return model;
    }
}

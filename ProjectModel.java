/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.util.Date;
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
    //constructor for the arraylist
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
    //creating a new project constructor i.e we don't yet know the id
    ProjectModel(int c, int u, int a, int p, int pr, 
            String t, String s, String m, String b, String cost,
            Date start, Date end) {
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
        String sql = "";
        //if logged in user has role of 0 then get projects belonging to the user
        //else if user has role of 1 then get projects belonging to that county
        //else if user has role of 5 then get all projects
        switch (Utils.user.getRole()) {
            case 0:
                sql = "SELECT * FROM " + DB.PROJECTS_TABLE + " "
                        + "WHERE user_id = "+ Utils.user.getID();
                break;
            case 1:
                sql = "SELECT * FROM " + DB.PROJECTS_TABLE + " "
                        + "WHERE county_id = "+ Utils.user.getCountyCode();
                break;
            case 5:
                sql = "SELECT * FROM " + DB.PROJECTS_TABLE;
                break;
            default:
                break;
        }
        
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
 
    @Override
    public void showList(JTable table) {
        ArrayList<ProjectModel> projectList = getList();
        //if (projectList.size() > 0) {
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setRowCount(0);
            Object[] row = new Object[9];
            for ( int i=0; i < projectList.size(); i++) {
                row[0] = projectList.get(i).getID();
                row[1] = projectList.get(i).getTitle();
                row[2] = projectList.get(i).getSponsor();
                row[3] = projectList.get(i).getManager();
                row[4] = projectList.get(i).getBudget();
                row[5] = projectList.get(i).getStartDate();
                row[6] = projectList.get(i).getEndDate();
                row[7] = projectList.get(i).getProgress();
                row[8] = projectList.get(i).getUser();
                model.addRow(row);
            }
        //}
        
    }
        
    /**
     * Adds a project to the database
     * @return boolean
     */
    @Override
    public boolean add() {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DB.PROJECTS_TABLE +"` "
                    + "(`user_id`, `county_id`, `title`, `sponsor`, `manager`, `activities`, `start_date`, `end_date`,"
                    + "`budget`, `actual_cost`,`personnel`,`progress`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            stmt.setInt(1, user_id);
            stmt.setInt(2, county_id);
            stmt.setString(3, title);
            stmt.setString(4, sponsor);
            stmt.setString(5, manager);
            stmt.setInt(6, activities);
            stmt.setDate(7, new java.sql.Date(start_date.getTime()));
            stmt.setDate(8, new java.sql.Date(end_date.getTime()));
            stmt.setString(9, budget);
            stmt.setString(10, actual_cost);
            stmt.setInt(11, personnel);
            stmt.setInt(12, progress);
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
            String sql = "UPDATE `"+ DB.PROJECTS_TABLE +"` SET "
                    + "`county_id` = ?, `title` = ?, `sponsor` = ?, `manager` = ?, `activities` = ?,"
                    + " `start_date` = ?, `end_date` = ?,`budget` = ?, `actual_cost` = ?,`personnel` = ?,`progress` = ?"
                    + " WHERE `id` = "+ id;
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);
            //NOTE: user_id is not updated
            stmt.setInt(1, county_id);
            stmt.setString(2, title);
            stmt.setString(3, sponsor);
            stmt.setString(4, manager);
            stmt.setInt(5, activities);
            stmt.setDate(6, new java.sql.Date(start_date.getTime()));
            stmt.setDate(7, new java.sql.Date(end_date.getTime()));
            stmt.setString(8, budget);
            stmt.setString(9, actual_cost);
            stmt.setInt(10, personnel);
            stmt.setInt(11, progress);
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

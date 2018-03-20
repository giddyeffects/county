/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project Database Table Model
 * 
 * @author 101794
 */
public class ProjectModel {
    private int id, county_id, user_id, activities, personnel, progress;
    private String title, sponsor, manager, budget, actual_cost;
    private Date start_date, end_date;
    
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
        end_date =end;
    }
    //getters
    
    
    //get project list
    ArrayList<ProjectModel> getProjectsList() {
        ArrayList<ProjectModel> projectsList = new ArrayList<>();
        String sql = "SELECT * FROM " + DBConnect.PROJECTS_TABLE + " "
                + "WHERE user_id = "+ Utils.user.getID();
        
        
        return projectsList;
    }
    
    public static boolean add(ProjectModel model) {
        boolean status = false;
        try {
            String sql = "INSERT INTO `"+ DBConnect.PROJECTS_TABLE +"` "
                    + "(`user_id`, `county_id`, `title`, `sponsor`, `manager`, ``, ``, ``)"
                    + " VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = Utils.db().connect().prepareStatement(sql);

            Utils.db().execute(stmt);
            status = true;
        } catch (SQLException e) {
        }
        return status;
    }
    
    public static boolean update(ProjectModel model) {
        boolean status = false;
        
        return status;
    }
    
    public static boolean delete(int id) {
        boolean status = false;
        
        return status;
    }
}

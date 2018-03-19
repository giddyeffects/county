/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

/**
 * User Database Model
 * 
 * @author 101794
 */
public class User {
    private String username, fullname, email, county;
    private int id, role;
    
    User(String u, String f, String e, String c, int r, int i) {
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
    //two roles for now. System ADmin and normal user
    public String getRole(){
        return (role == 5)?"System Administrator":"Normal User";
    }
    
    public int getID(){
        return id;
    }
}

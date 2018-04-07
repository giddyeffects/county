/*
 * Object Oriented Programming Principles
 * End of Semester class project
 * 
 */
package Project;

/**
 * Database Model interface
 * @author 101794
 * @param <T>
 */
public interface Model<T> {

    /**
     * Add model to database
     * @return
     */
    boolean add();

    /**
     * Update database model
     * @param id
     * @return
     */
    boolean update(int id);

    /**
     * Delete model from database
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * Find model in the database
     * @param id
     * @return
     */
    T find(int id);

    /**
     * Get ArrayList of the models database table rows
     * @return
     */
    java.util.ArrayList<T> getList();
    /**
     * Show the ArrayList on a JTable
     * @param table 
     */
    public void showList(javax.swing.JTable table);
}

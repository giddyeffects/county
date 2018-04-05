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
    boolean add(T model);
    boolean update(T model);
    boolean delete(int id);
    T find(int id);
    java.util.ArrayList<T> getList();
}

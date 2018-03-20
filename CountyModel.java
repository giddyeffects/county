/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.sql.ResultSet;
import javax.swing.JComboBox;

/**
 *
 * @author user
 */
public class CountyModel {

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
    
}

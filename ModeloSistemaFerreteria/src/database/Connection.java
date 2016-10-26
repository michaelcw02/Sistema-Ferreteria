/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import com.mysql.jdbc.Statement;
import java.sql.*;

/**
 *
 * @author Michael Chen W.
 */
public class Connection {
    
    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/localhost" + "user = root & password = root");
            return true;            
        } catch (Exception e) {
            
        }
        return false;
    }
    
}

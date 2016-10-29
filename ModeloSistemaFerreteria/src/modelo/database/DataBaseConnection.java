/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.database;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


/**
 *
 * @author Michael Chen W.
 */
public class DataBaseConnection {
    
    public DataBaseConnection(){
        cnx = this.getConnection();            
    }
    public Connection getConnection(){
        try {
            
            Properties prop = new Properties();
            URL resourceUrl = this.getClass().getResource(PROPERTIES_FILE_NAME);
            File file = new File(resourceUrl.toURI());    
            prop.load(new BufferedReader(new InputStreamReader(new FileInputStream(file))));
            String driver = prop.getProperty("database_driver");
            String server = prop.getProperty("database_server");
            String port = prop.getProperty("database_port");
            String user = prop.getProperty("database_user");
            String password = prop.getProperty("database_password");
            String database = prop.getProperty("database_name");
            
            String URL_conexion="jdbc:mysql://"+ server+":"+port+"/"+database+"?user="+user+"&password="+password;
            Class.forName(driver).newInstance();
            return DriverManager.getConnection(URL_conexion);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        } 
        return null;
    }

    public int executeUpdate(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement);
            return stm.getUpdateCount();
        } catch (SQLException ex) {
            return 0;
        }
    }
 
    public ResultSet executeUpdateWithKeys(String statement) {
        try {
            Statement stm = cnx.createStatement();
            stm.executeUpdate(statement, Statement.RETURN_GENERATED_KEYS);
            return stm.getGeneratedKeys();
        } catch (SQLException ex) {
            return null;
        }
    }
    
    public ResultSet executeQuery(String statement){
        try {
            Statement stm = cnx.createStatement();
            return stm.executeQuery(statement);
        } catch (SQLException ex) {
        }
        return null;
    }
    
    
    public static final String PROPERTIES_FILE_NAME="/ferreteria.properties";        
    Connection cnx;
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecyo_progra_3.Infraestructure.Config;

import java.util.Properties;
import java.sql.*;

/**
 *
 * @author Usuario
 */




public class MySQLConfig {
    
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASS = "";
    
    
    
    private static Properties propsDB()  {
        
        var props = new Properties();
        
        props.setProperty("user", USER);
        props.setProperty("password", PASS);
        

        
        return props;
    }
    
    
    /*
    Falta implementar con HikariCP y Datasource.
    
    */
    
   
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,propsDB());
    }
    
    
    
    
}

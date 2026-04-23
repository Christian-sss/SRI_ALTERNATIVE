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
    
    private static final String URL = "jdbc:mysql://shortline.proxy.rlwy.net:52566/sri_db";
    private static final String USER = "root";
    private static final String PASS = "rgnrzZbVeaFeOWCkYmqskSDuQJOKtQdF";




    private static Properties propsDB()  {
        
        var props = new Properties();
        
        props.setProperty("user", USER);
        props.setProperty("password", PASS);

        return props;
    }
    
    

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,propsDB());
    }
    
    
    
    
}

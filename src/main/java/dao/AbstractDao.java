/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author nur13
 */
public abstract class AbstractDao {
    public Connection connect() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");//Java 7 ve asaghi versiyalarda yazilmasi lazimdir
        
        String username = "TESTS";
        String password = "12345";
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", username, password);
        return connection;
    }
    
    
}

      

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpt.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badan
 */
public class DBConnection {
    private Connection connection;
    
    public Connection getConnection(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection("jdbc:sqlserver://localhost;databaseName=Halloween", "sa", "123456");
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}

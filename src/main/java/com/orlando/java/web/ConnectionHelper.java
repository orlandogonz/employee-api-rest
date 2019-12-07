
package com.orlando.java.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author orlando
 * [www.github.com/orlandogonz]
 * [www.linkedin.com/in/orlandogonz]
 * [orlandogonz.dev@gmail.com]
 */

//Clase donde crearemos la conexión a la DB
//Creamos el administrador a la conexión de base de datos
public class ConnectionHelper {
    
    //Atributos de la clase ConnectionHelper
    private String url;
    private String user;
    private String pw;
    private static ConnectionHelper instance;
    
    private ConnectionHelper(){
        
        String driver;
        
        try {
            Properties bundle = new Properties();
            bundle.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));
            driver = bundle.getProperty("jdbc.driver");
            Class.forName(driver);
            
            url = bundle.getProperty ("jdbc.url");

            user = bundle.getProperty ("jdbc.user");
            pw   = bundle.getProperty ("jdbc.pw");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    //Utilizamos la interfaz Connection
    //Para trabajar con DB, debemos conseguir una conexión, es decir un objeto del tipo Connection
    public static Connection getConnection() throws SQLException{
        
        if (instance == null) {
            instance = new ConnectionHelper();
	}
        
        try {
            return DriverManager.getConnection (instance.url, instance.user, instance.pw);
        } catch (SQLException e) {
            throw e;
        }
        
    }
    
    //Cerramos la conexión a la base de datos
    public static void close (Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
	} catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

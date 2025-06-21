
package com.systematic.app.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author anthony
 */
public class DBConnection {
    
    private static final String BASE_DATOS = "biblioteca";
    private static final String URL = "jdbc:mysql://localhost:3306/"+BASE_DATOS;
    private static final String USER = "ayerenmartinez";
    private static final String PASSWORD = "root";
    
    //Iniciar 
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (ClassNotFoundException exception) {
             System.out.println("Error al cargar el driver: " + exception.getMessage());
        }catch(SQLException exception){
             System.out.println("Error de conexión: " + exception.getMessage());
        }     
        
        return null;
    }
    
    //cerrar conexion
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    
}


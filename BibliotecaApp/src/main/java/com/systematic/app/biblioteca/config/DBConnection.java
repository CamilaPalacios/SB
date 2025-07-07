package com.systematic.app.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Clase para manejar conexiones a la base de datos MySQL
 */
public class DBConnection {
    
    private static final String BASE_DATOS = "biblioteca";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BASE_DATOS;
    private static final String USER = "Hancock";
    private static final String PASSWORD = "boahancock12345.";
    
    // Configuraciones adicionales para el pool de conexiones
    private static final Properties connectionProperties = new Properties();
    
    static {
        try {
            // Cargar el driver solo una vez
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Configurar propiedades de conexión
            connectionProperties.put("user", USER);
            connectionProperties.put("password", PASSWORD);
            connectionProperties.put("useSSL", "false");
            connectionProperties.put("autoReconnect", "true");
            connectionProperties.put("characterEncoding", "UTF-8");
            connectionProperties.put("useUnicode", "true");
            connectionProperties.put("serverTimezone", "UTC");
            connectionProperties.put("allowPublicKeyRetrieval", "true");
            
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("No se pudo cargar el driver JDBC: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene una conexión a la base de datos
     * @return Objeto Connection válido
     * @throws SQLException Si ocurre un error al establecer la conexión
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, connectionProperties);
            
            // Verificar que la conexión sea válida
            if (connection == null || connection.isClosed()) {
                throw new SQLException("No se pudo establecer una conexión válida");
            }
            
            return connection;
        } catch (SQLException e) {
            throw new SQLException("Error al conectar a la base de datos: " + e.getMessage(), e);
        }
    }
    
    /**
     * Cierra una conexión de forma segura
     * @param connection Conexión a cerrar
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                // Podrías lanzar una excepción personalizada aquí si lo prefieres
            }
        }
    }
    
    /**
     * Realiza un rollback de forma segura
     * @param connection Conexión sobre la que hacer rollback
     */
    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed() && !connection.getAutoCommit()) {
                    connection.rollback();
                }
            } catch (SQLException e) {
                System.err.println("Error al hacer rollback: " + e.getMessage());
            }
        }
    }
}
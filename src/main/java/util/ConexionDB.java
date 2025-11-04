package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/happy_feet_veterinaria";
    private static final String USER ="root";
    private static final String PASS = "1234567890";
    
    public static Connection conectar(){
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Conexion DB : Error intentado conectar ala DB: "+ e.getMessage());
            return null;
        }
    }
}

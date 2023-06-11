
package com.proyectoasistencia;


import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConexionBD {
    private static String url = "jdbc:mysql://localhost:3306/controlasistencia?serverTimezone=America/Mexico_City";
    private static String username = "root";
    private static String password = "";
    private static Connection conn;
    
    public static Connection getInstance() {
        if(conn == null){
            try {
                conn = DriverManager.getConnection(url,username,password);
                System.out.println("Conexion establecida");
            } catch (SQLException ex) {
                Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Hubo un error en la conexion");
            }
        }
        return conn;
    }
    
}

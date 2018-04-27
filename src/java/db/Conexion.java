/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 *
 * @author Edgar
 */
public class Conexion {
    public static Connection conexion;
    private static final String URL = "jdbc:mysql://localhost/";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB = "condominio";
  
    public static synchronized void conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(URL + DB, USER, PASS);
            System.out.println("Conexión Exitosa");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se pudo conectar");
        } catch (SQLException ex) {
            System.out.println("Error de MySQL");
        }
    }

    public static synchronized Connection getConexion() {
        conectar();
        return conexion;
    }
    
    //Metodo utilizado para cerrar el callablestatemente
    public static synchronized void cerrarCall(CallableStatement cl) {
        try{
            cl.close();
        } catch (SQLException e) {
        }
    }
    //Metodo utilizado para cerrar el resulset de datos
    public static synchronized void cerrarConexion(ResultSet rs) {
        try{
            rs.close();
        } catch (SQLException e) {
        }
    }
    //Metodo utilizado para cerrar la conexion
    public static synchronized void cerrarConexion(Connection cn) {
        try{
            cn.close();
        } catch (SQLException e) {
        }
    }
    //Metodo utilizado para deshacer los cambios en la base de datos
    public static synchronized void deshacerCambios(Connection cn) {
        try{
            cn.rollback();
        } catch (SQLException e) {
        }
    }
}

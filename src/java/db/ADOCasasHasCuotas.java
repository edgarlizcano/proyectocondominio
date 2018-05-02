/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Casas;
import modelo.CasasHasCuotas;
import modelo.Cuotas;

/**
 *
 * @author Edgar
 */
public class ADOCasasHasCuotas {
    
    public static synchronized boolean pagarCuota(int idCasa, int idCuota, int idCuenta) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL pagoCuota(?,?,?)}";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            
            cl.setInt(1, idCasa);
            cl.setInt(2, idCuota);
            cl.setInt(3, idCuenta);
            
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = cl.executeUpdate() == 1;
            if (rpta) {
                //Confirmamos la transaccion
                cn.commit();
            } else {
                //Negamos la transaccion
                Conexion.deshacerCambios(cn);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.deshacerCambios(cn);
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return rpta;
    }
    
    public static synchronized ArrayList<CasasHasCuotas> obtenerCuotasPendientes() {
        //El array que contendra todos nuestros productos
        ArrayList<CasasHasCuotas> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerCuotasPendientes()}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                boolean estado = rs.getBoolean("estadoPago");
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, estado);
                //Lo adicionamos a nuestra lista
                lista.add(cc);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }
    
    public static synchronized ArrayList<CasasHasCuotas> obtenerCuotasPagadas() {
        //El array que contendra todos nuestros productos
        ArrayList<CasasHasCuotas> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerCuotasPagadas()}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                boolean estado = rs.getBoolean("estadoPago");
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, estado);
                //Lo adicionamos a nuestra lista
                lista.add(cc);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }
    
    public static synchronized ArrayList<CasasHasCuotas> obtenerCuotasPagadasByCasa(int idCasa) {
        ArrayList<CasasHasCuotas> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            
            String call = "{CALL obtenerCuotasPagadasByCasa(?)}";
            
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            cl.setInt(1, idCasa);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            
            while (rs.next()) {
                
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                boolean estado = rs.getBoolean("estadoPago");
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, estado);
                //Lo adicionamos a nuestra lista
                lista.add(cc);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }
    
    public static synchronized ArrayList<CasasHasCuotas> obtenerCuotasPendientesByCasa(int idCasa) {
        ArrayList<CasasHasCuotas> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            
            String call = "{CALL obtenerCuotasPendientesByCasa(?)}";
            
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            cl.setInt(1, idCasa);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            
            while (rs.next()) {
                
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                boolean estado = rs.getBoolean("estadoPago");
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, estado);
                //Lo adicionamos a nuestra lista
                lista.add(cc);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }
    
    public static synchronized ArrayList<CasasHasCuotas> obtenerCuotasByCasa(int idCasa) {
        ArrayList<CasasHasCuotas> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            
            String call = "{CALL obtenerCuotasByCasa(?)}";
            
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            cl.setInt(1, idCasa);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            
            while (rs.next()) {
                
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                boolean estado = rs.getBoolean("estadoPago");
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, estado);
                //Lo adicionamos a nuestra lista
                lista.add(cc);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return lista;
    }
    
}

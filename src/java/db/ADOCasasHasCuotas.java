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
import java.sql.Types;
import java.util.ArrayList;
import modelo.Bancos;
import modelo.Casas;
import modelo.CasasHasCuotas;
import modelo.Cuotas;
import modelo.Pagos;
import modelo.Usuarios;

/**
 *
 * @author Edgar
 */
public class ADOCasasHasCuotas {
    //Metodo utilizado para insertar un Usuario a nuestra Base de datos
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
    //Metodo utilizado para actualizar un Usuarios a nuestra Base de datos
    
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
                Pagos p = new Pagos();
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                Bancos b = new Bancos();
                
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                b.setNombreBanco(rs.getString("nombreBanco"));
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                p.setBanco(b);
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, p);
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
    
    public static synchronized ArrayList<CasasHasCuotas> obtenerPagosByHabitante(int id) {
        //El array que contendra todos nuestros pagos
        ArrayList<CasasHasCuotas> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagosByHabitante(?)}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setInt(1, id);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Pagos p = new Pagos();
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, p);
                
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
    
    public static synchronized Pagos obtenerPagoById(int idPago) {
        Pagos p = new Pagos();
        Bancos b = new Bancos();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            
            String call = "{CALL obtenerPagoById(?)}";
            
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            cl.setInt(1, idPago);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            
            while (rs.next()) {
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                p.setEstatus(rs.getBoolean("estatus"));
                b.setIdBancos(rs.getInt("idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                p.setBanco(b);
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
        return p;
    }
    
    public static synchronized boolean confirmarPago(int id, int idUser) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL confirmarPago(?,?)}";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            
            cl.setInt(1, id);
            cl.setInt(2, idUser);
            
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

    public static ArrayList obtenerPagosConfirmados() {
        //El array que contendra todos nuestros pagos
        ArrayList<CasasHasCuotas> lista = null;
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagosConfirmados()}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            lista = new ArrayList<>();
            
            while (rs.next()) {
                Pagos p = new Pagos();
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                p.setUsuario(u);
                p.setBanco(b);
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, p);
                
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
    
    public static ArrayList obtenerPagosEnEspera() {
        //El array que contendra todos nuestros pagos
        ArrayList<CasasHasCuotas> lista = null;
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagosEnEspera()}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            lista = new ArrayList<>();
            
            while (rs.next()) {
                Pagos p = new Pagos();
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
                u.setNombreUsuario("En Espera");
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                p.setUsuario(u);
                p.setBanco(b);
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, p);
                
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

    public static ArrayList obtenerPagoByFecha(String inicio, String fin) {
        //El array que contendra todos nuestros pagos
        ArrayList<CasasHasCuotas> lista = null;
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagosByFechas(?,?)}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setString(1, inicio);
            cl.setString(2, fin);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            lista = new ArrayList<>();
            
            while (rs.next()) {
                Pagos p = new Pagos();
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
                
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                p.setBanco(b);
                p.setUsuario(u);
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, p);
                
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

    public static ArrayList obtenerPagoByCasa(int idCasa) {
        //El array que contendra todos nuestros pagos
        ArrayList<CasasHasCuotas> lista = null;
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagosByCasa(?)}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setInt(1, idCasa);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            lista = new ArrayList<>();
            
            while (rs.next()) {
                Pagos p = new Pagos();
                Casas ca = new Casas();
                Cuotas cu = new Cuotas();
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
                
                u.setIdUsuario(rs.getInt("Usuarios_idUsuario"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                
                cu.setIdCuotas(rs.getInt("idCuotas"));
                cu.setNombre(rs.getString("nombre"));
                
                ca.setIdCasas(rs.getInt("idCasa"));
                ca.setNombreCasa(rs.getString("nombreCasa"));
                p.setUsuario(u);
                p.setBanco(b);
                CasasHasCuotas cc = new CasasHasCuotas(ca, cu, p);
                
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

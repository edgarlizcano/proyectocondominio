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
import modelo.Cuentas;
import modelo.Pagos;
import modelo.Usuarios;

/**
 *
 * @author Edgar
 */
public class ADOPagos {
    //Metodo utilizado para insertar un Usuario a nuestra Base de datos
    public static synchronized boolean insertarPago(Pagos p, Casas ca) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL agregarPago(?,?,?,?,?,?,?,?)}";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //Como el codigo se autogenera y es del tipo OUT en el procedimiento
            //almacenado le decimos que es OUT y el del tipo Integer en Java
            cl.registerOutParameter(1, Types.INTEGER);
            //El siguiente parametro del procedimiento almacenado es el nombre
            cl.setDouble(2, p.getMonto());
            cl.setString(3, p.getFecha());
            cl.setString(4, p.getCedulaDepositante());
            cl.setString(5, p.getNombreApellido());
            cl.setString(6, p.getReferencia());
            cl.setInt(7, p.getBanco().getIdBancos());
            cl.setInt(8, ca.getCuenta().getIdCuenta());
            
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
    
    public static synchronized ArrayList<Pagos> obtenerPagos() {
        //El array que contendra todos nuestros productos
        ArrayList<Pagos> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagos()}";
            
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
                Bancos b = new Bancos();
                
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                
                b.setIdBancos(rs.getInt("idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getDouble("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                
                p.setBanco(b);
                //Lo adicionamos a nuestra lista
                lista.add(p);
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
    
    public static synchronized ArrayList<Pagos> obtenerPagosByCuenta(int idCuenta) {
        //El array que contendra todos nuestros pagos
        ArrayList<Pagos> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerPagosByCuenta(?)}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setInt(1, idCuenta);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Pagos p = new Pagos();
                Bancos b = new Bancos();
                Usuarios u = new Usuarios();
                
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getDouble("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setEstatus(rs.getBoolean("estatus"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                u.setIdUsuario(rs.getInt("Usuarios_idUsuario"));
                b.setIdBancos(rs.getInt("idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                
                p.setUsuario(u);
                p.setBanco(b);
                //Lo adicionamos a nuestra lista
                lista.add(p);
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
        Cuentas c = new Cuentas();
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
                p.setMonto(rs.getDouble("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                p.setEstatus(rs.getBoolean("estatus"));
                b.setIdBancos(rs.getInt("idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                c.setIdCuenta(rs.getInt("Cuentas_idCuenta"));
                
                p.setCuenta(c);
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
        ArrayList<Pagos> lista = null;
        
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
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                Cuentas c = new Cuentas();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getDouble("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                p.setEstatus(rs.getBoolean("estatus"));
                b.setIdBancos(rs.getInt("Bancos_idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                c.setIdCuenta(rs.getInt("Cuentas_idCuenta"));
                
                p.setCuenta(c);
                p.setBanco(b);
                p.setUsuario(u);
                
                //Lo adicionamos a nuestra lista
                
                lista.add(p);
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
        ArrayList<Pagos> lista = null;
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
                Bancos b = new Bancos();
                Cuentas c = new Cuentas();
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getDouble("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                p.setEstatus(rs.getBoolean("estatus"));
                b.setIdBancos(rs.getInt("Bancos_idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                c.setIdCuenta(rs.getInt("Cuentas_idCuenta"));
                
                p.setCuenta(c);
                p.setBanco(b);
                
                //Lo adicionamos a nuestra lista
                
                lista.add(p);
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
        ArrayList<Pagos> lista = null;
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
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                Cuentas c = new Cuentas();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
//                u.setNombreUsuario(rs.getString("nombreUsuario"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                p.setEstatus(rs.getBoolean("estatus"));
                b.setIdBancos(rs.getInt("Bancos_idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                c.setIdCuenta(rs.getInt("Cuentas_idCuenta"));
                
                p.setCuenta(c);
                p.setBanco(b);
                p.setUsuario(u);
                
                //Lo adicionamos a nuestra lista
                
                lista.add(p);
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
        ArrayList<Pagos> lista = null;
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
                Usuarios u = new Usuarios();
                Bancos b = new Bancos();
                Cuentas c = new Cuentas();
                
                b.setNombreBanco(rs.getString("nombreBanco"));
                //u.setNombreUsuario(rs.getString("nombreUsuario"));
                
                p.setIdPagos(rs.getInt("idPagos"));
                p.setMonto(rs.getFloat("monto"));
                p.setFecha(rs.getString("fecha"));
                p.setCedulaDepositante(rs.getString("cedulaDepositante"));
                p.setNombreApellido(rs.getString("nombre_apellido"));
                p.setReferencia(rs.getString("referencia"));
                p.setEstatus(rs.getBoolean("estatus"));
                b.setIdBancos(rs.getInt("Bancos_idBancos"));
                b.setNombreBanco(rs.getString("nombreBanco"));
                c.setIdCuenta(rs.getInt("Cuentas_idCuenta"));
                
                p.setCuenta(c);
                p.setBanco(b);
                p.setUsuario(u);
                
                //Lo adicionamos a nuestra lista
                
                lista.add(p);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import modelo.Habitantes;
import modelo.Usuarios;

/**
 *
 * @author Edgar
 */
public class ADOUsuarios {
    //Metodo utilizado para insertar un Usuario a nuestra Base de datos
    public static synchronized boolean insertarUsuario(Usuarios user, Habitantes hab) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL agregarUsuario(?,?,?,?,?,?,?,?,?)}";
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
            cl.setString(2, user.getNombreUsuario());
            cl.setString(3, user.getClave());
            cl.setString(4, user.getEmail());
            cl.setString(5, hab.getCedula());
            cl.setString(6, hab.getNombres());
            cl.setString(7, hab.getApellidos());
            cl.setString(8, hab.getFechaNacimiento());
            cl.setInt(9, hab.getCasas().getIdCasas());
            
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
    public static synchronized boolean actualizarUsuario(Usuarios user, String newPass) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL actualizarUsuario(?,?,?)}";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //El primer parametro del procedimiento almacenado es el codigo
            cl.setInt(1, user.getIdUsuario());
            //El siguiente parametro del procedimiento almacenado es el nombre
            cl.setString(2, user.getEmail());
            //Y por ultimo el precio
            cl.setString(3, user.getClave());
            cl.setString(4, newPass);
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
    
    public static synchronized boolean eliminarUsuario(Usuarios user) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL eliminarUsuario(?)}";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            cl = cn.prepareCall(call);
            //El primer parametro del procedimiento almacenado es el codigo
            cl.setInt(1, user.getIdUsuario());
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
    //Metodo utilizado para obtener todos los productos de nuestra base de datos
    public static synchronized ArrayList<Usuarios> obtenerUsuarios() {
        //El array que contendra todos nuestros productos
        ArrayList<Usuarios> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerUsuarios()}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Usuarios u = new Usuarios();
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                u.setIdUsuario(rs.getInt("idUsuario"));
                u.setNombreUsuario(rs.getString("nombreUsuario"));
                u.setEmail(rs.getString("email"));
                u.setUltimaConexion(rs.getDate("ultimaConexion"));
                u.setEstatus(rs.getBoolean("estatus"));
                //Lo adicionamos a nuestra lista
                lista.add(u);
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
    //Metodo utilizado para obtener un producto específico de nuestra base de datos
    public static synchronized Usuarios getUsuarioByNombre(String nombre) {
        Usuarios usuario = new Usuarios();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerUsuarioByNombre(?)}";
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setString(1, nombre);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto usuario
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUltimaConexion(rs.getDate("ultimaConexion"));
                usuario.setEstatus(rs.getBoolean("estatus"));
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
        return usuario;
    }
    
    public static synchronized Usuarios getUsuarioByID(int id) {
        Usuarios usuario = new Usuarios();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerUsuarioById(?)}";
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setInt(1, id);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto usuario
                usuario.setIdUsuario(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setEmail(rs.getString("email"));
                usuario.setUltimaConexion(rs.getDate("ultimaConexion"));
                usuario.setEstatus(rs.getBoolean("estatus"));
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
        return usuario;
    }
    
    public static synchronized boolean loginUsuario(Usuarios user) {
        boolean rpta = false;
        Usuarios usuario = new Usuarios();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs = null;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerUsuarioByNombre(?)}";
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setString(1, user.getNombreUsuario());
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto usuario
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setClave(rs.getString("clave"));
                usuario.setUltimaConexion(rs.getDate("ultimaConexion"));
                
                if (usuario.getClave().equals(user.getClave()) && usuario.getNombreUsuario().equals(user.getNombreUsuario())){
                    rpta = true;
                }
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
        return rpta;
    }
}

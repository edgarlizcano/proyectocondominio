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
import modelo.Calles;
import modelo.Casas;
import modelo.Habitantes;

/**
 *
 * @author Edgar
 */
public class ADOHabitantes {
    //Metodo utilizado para insertar un Usuario a nuestra Base de datos
    public static synchronized boolean insertarHabitante(Habitantes hab) {
        Connection cn = null;
        CallableStatement cl = null;
        boolean rpta = false;
        try {
            //Nombre del procedimiento almacenado y como espera tres parametros
            //le ponemos 3 interrogantes
            String call = "{CALL agregarHabitante(?,?,?,?,?,?)}";
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
            cl.setString(2, hab.getCedula());
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
    
    public static synchronized ArrayList<Habitantes> obtenerHabitantes() {
        //El array que contendra todos nuestros productos
        ArrayList<Habitantes> lista = new ArrayList<>();
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerHabitantes()}";
            
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                Habitantes h = new Habitantes();
                Casas c = new Casas();
                Calles ca = new Calles();
                //Obtenemos los valores de la consulta y creamos
                //nuestro objeto producto
                
                h.setIdHabitante(rs.getInt("idHabitante"));
                h.setCedula(rs.getString("cedula"));
                h.setNombres(rs.getString("nombres"));
                h.setApellidos(rs.getString("apellidos"));
                h.setFechaNacimiento(rs.getString("fechaNacimiento"));
                h.setEstatus(rs.getBoolean("estatus"));
                
                c.setIdCasas(rs.getInt("idCasa"));
                c.setNombreCasa("nombreCasa");
                
                ca.setIdCalles(rs.getInt("idCalles"));
                ca.setNombreCalle(rs.getString("nombreCalle"));
                c.setCalles(ca);
                h.setCasas(c);
                
                //Lo adicionamos a nuestra lista
                lista.add(h);
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
    
    public static synchronized Habitantes obtenerHabitante(int idUser) {
        //El array que contendra todos nuestros productos
        Habitantes h = new Habitantes ();
        Casas c = new Casas();
        Calles ca = new Calles();
        
        Connection cn = null;
        CallableStatement cl = null;
        ResultSet rs;
        try {
            
            //Nombre del procedimiento almacenado
            String call = "{CALL obtenerHabitanteByUsuario(?)}";
            cn = Conexion.getConexion();
            cl = cn.prepareCall(call);
            cl.setInt(1, idUser);
            //La sentencia lo almacenamos en un resulset
            rs = cl.executeQuery();
            //cl.execute();
            //rs = cl.getResultSet();
            //Consultamos si hay datos para recorrerlo
            //e insertarlo en nuestro array
            while (rs.next()) {
                h.setIdHabitante(rs.getInt("idHabitante"));
                h.setCedula(rs.getString("cedula"));
                h.setNombres(rs.getString("nombres"));
                h.setApellidos(rs.getString("apellidos"));
                h.setFechaNacimiento(rs.getString("fechaNacimiento"));
                h.setEstatus(rs.getBoolean("estatus"));
                
                c.setIdCasas(rs.getInt("idCasa"));
                c.setNombreCasa(rs.getString("nombreCasa"));
                
                ca.setIdCalles(rs.getInt("idCalles"));
                ca.setNombreCalle(rs.getString("nombreCalle"));
                
                c.setCalles(ca);
                h.setCasas(c);
            }
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            System.out.println("Error SQL: "+e.getMessage());
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        } catch (Exception e) {
            System.out.println("Error general: "+e.getMessage());
            Conexion.cerrarCall(cl);
            Conexion.cerrarConexion(cn);
        }
        return h;
    }
    
    /*
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
    */
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.ADOUsuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Casas;
import modelo.Habitantes;
import modelo.Usuarios;

/**
 *
 * @author Edgar
 */
public class ControladorUsuarios extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            String accion = request.getParameter("accion");
            switch (accion) {
                case "RegistroUsuario":
                    this.registrarUsuario(request, response);
                    break;
                case "ActualizarUsuario":
                    this.actualizarUsuario(request, response);
                    break;
                case "EliminarUsuario":
                    this.eliminarUsuario(request, response);
                    break;
                /*case "RegistrarVenta":
                    this.registrarVenta(request, response);
                    break;*/
                default:
                    break;
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        
        Usuarios user = new Usuarios();
        Habitantes hab = new Habitantes();
        Casas casa = new Casas();
        casa.setIdCasas(Integer.parseInt(request.getParameter("numeroCasa")));
        
        user.setNombreUsuario(request.getParameter("nombreUsuario"));
        user.setEmail(request.getParameter("email"));
        user.setClave(request.getParameter("clave"));
        hab.setCedula(request.getParameter("cedula"));
        hab.setNombres(request.getParameter("nombres"));
        hab.setApellidos(request.getParameter("apellidos"));
        hab.setFechaNacimiento(request.getParameter("fechaNac"));
        hab.setCasas(casa);
        
        boolean rpta;
        rpta = ADOUsuarios.insertarUsuario(user, hab);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("result.jsp?men=Se registro el Usuario de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("result.jsp?men=No se registro el Usuario");
        }
    }
    
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Usuarios user = new Usuarios();
        
        user.setIdUsuario(Integer.parseInt(request.getParameter("id")));
        user.setNombreUsuario(request.getParameter("nombreUsuario"));
        user.setEmail(request.getParameter("email"));
        user.setClave(request.getParameter("clave"));
        String newPass = request.getParameter("newPass");
        
        boolean rpta;
        rpta = ADOUsuarios.actualizarUsuario(user, newPass);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("result.jsp?men=Se actualizó el Usuario de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("result.jsp?men=No se actualizó el Usuario");
        }
    }
    
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Usuarios user = new Usuarios();
        
        user.setIdUsuario(Integer.parseInt(request.getParameter("id")));
        
        boolean rpta;
        rpta = ADOUsuarios.eliminarUsuario(user);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("result.jsp?men=Se eliminó el Usuario de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("result.jsp?men=No se eliminó el Usuario");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   

}

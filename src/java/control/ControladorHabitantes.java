/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.ADOHabitantes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Casas;
import modelo.Habitantes;

/**
 *
 * @author Edgar
 */
public class ControladorHabitantes extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
            
            switch (accion) {
                case "RegistroHabitante":
                    this.registrarHabitante(request, response);
                    break;
                /*case "ActualizarUsuario":
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

    private void registrarHabitante(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        Habitantes actual = (Habitantes) request.getSession().getAttribute("habitante");
        Casas casa = actual.getCasas();
        
        Habitantes hab = new Habitantes();
        
        hab.setCedula(request.getParameter("cedula"));
        hab.setNombres(request.getParameter("nombres"));
        hab.setApellidos(request.getParameter("apellidos"));
        hab.setFechaNacimiento(request.getParameter("fechaNac"));
        hab.setCasas(casa);
        
        boolean rpta;
        rpta = ADOHabitantes.insertarHabitante(hab);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("micasa.jsp?men=Se registro el Habitante de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("micasa.jsp?men=No se registro el Habitante");
        }
    }

}

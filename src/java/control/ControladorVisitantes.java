/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.ADOVisitantes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Habitantes;
import modelo.Visitantes;

/**
 *
 * @author Edgar
 */
public class ControladorVisitantes extends HttpServlet {

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
                case "RegistroVisitante":
                    this.registrarVisitante(request, response);
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

    private void registrarVisitante(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        Visitantes visitante = new Visitantes();
        
        visitante.setCedula(request.getParameter("cedula"));
        visitante.setNombre(request.getParameter("nombres"));
        visitante.setApellido(request.getParameter("apellidos"));
        
        boolean rpta;
        rpta = ADOVisitantes.insertarVisitante(visitante);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("visitantes.jsp?men=Se registro el visitante de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("visitantes.jsp?men=No se registro el visitante");
        }
    }

}

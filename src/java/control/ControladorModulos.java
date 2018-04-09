/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.ADOModulos;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Modulos;

/**
 *
 * @author Edgar
 */
public class ControladorModulos extends HttpServlet {

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
            case "RegistroModulo":
                this.registrarModulo(request, response);
                break;
            case "AsignaModulo":
                this.asignarModulo(request, response);
                break;
            /*case "AnadirCarrito":
                this.añadirCarrito(request, response);
                break;
            case "RegistrarVenta":
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

    private void registrarModulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Modulos modulo = new Modulos();
        
        modulo.setNombreModulo(request.getParameter("nombreModulo"));
        modulo.setUrlModulo(request.getParameter("urlModulo"));
        modulo.setVisible(false);
        
        if (request.getParameter("visible") != null){
            modulo.setVisible(true);
        }
        
        boolean rpta;
        
        rpta = ADOModulos.insertarModulo(modulo);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("modulos.jsp?men=Se registro el Módulo de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("modulos.jsp?men=No se registro el Módulo");
        }
    }

    private void asignarModulo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int modulo = Integer.parseInt(request.getParameter("modulo"));
        int perfil = Integer.parseInt(request.getParameter("perfil"));
        boolean rpta;
        
        rpta = ADOModulos.asignarModulo(modulo, perfil);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("asignarmodulos.jsp?men=Se registro el Módulo de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("asignarmodulos.jsp?men=No se registro el Módulo");
        }
    }

}

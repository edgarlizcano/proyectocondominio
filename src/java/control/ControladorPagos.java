/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.ADOPagos;
import db.ADOUsuarios;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bancos;
import modelo.Casas;
import modelo.Cuotas;
import modelo.Pagos;
import modelo.Usuarios;

/**
 *
 * @author Edgar
 */
public class ControladorPagos extends HttpServlet {

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
                case "RegistroPago":
                    this.registrarPago(request, response);
                    break;
                case "ConfirmarPago":
                    this.confirmarPago(request, response);
                    break;
                /*case "EliminarUsuario":
                    this.eliminarUsuario(request, response);
                    break;
                case "RegistrarVenta":
                    this.registrarVenta(request, response);
                    break;*/
                default:
                    break;
            }
        } catch (ParseException ex) {
            Logger.getLogger(ControladorUsuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void registrarPago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        Pagos pago = new Pagos();
        Cuotas cuota = new Cuotas();
        Casas casa = new Casas();
        Bancos banco = new Bancos();
        
        pago.setMonto(Double.parseDouble(request.getParameter("monto")));
        pago.setFecha(request.getParameter("fecha"));
        pago.setCedulaDepositante(request.getParameter("cedula"));
        pago.setNombreApellido(request.getParameter("nombre"));
        pago.setReferencia(request.getParameter("referencia"));
        banco.setIdBancos(Integer.parseInt(request.getParameter("idBanco")));
        casa.setIdCasas(Integer.parseInt(request.getParameter("numeroCasa")));
        cuota.setIdCuotas(Integer.parseInt(request.getParameter("cuota")));
        pago.setBanco(banco);
        
        boolean rpta;
        rpta = ADOPagos.insertarPago(pago, casa, cuota);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("mispagos.jsp?men=Se registro el Pago de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("mispagos.jsp?men=No se registro el Pago");
        }
    }
    
    private void confirmarPago(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        
        boolean rpta;
        rpta = ADOPagos.confirmarPago(id);
        if (rpta) {
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            response.sendRedirect("pagos.jsp?men=Se confirmó el pago de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("pagos.jsp?men=No se pudo confirmar el pago");
        }
    }
    /*
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
    }*/

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

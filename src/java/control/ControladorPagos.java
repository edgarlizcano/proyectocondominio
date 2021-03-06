/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.ADOPagos;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bancos;
import modelo.Casas;
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
                case "ConsultaPorFiltro":
                    this.pagosPorFiltros(request, response);
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
    
    private void registrarPago(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        Pagos pago = new Pagos();
        Casas casa = new Casas();
        Bancos banco = new Bancos();
        
        pago.setMonto(Double.parseDouble(request.getParameter("monto")));
        pago.setFecha(request.getParameter("fecha"));
        pago.setCedulaDepositante(request.getParameter("cedula"));
        pago.setNombreApellido(request.getParameter("nombre"));
        pago.setReferencia(request.getParameter("referencia"));
        banco.setIdBancos(Integer.parseInt(request.getParameter("idBanco")));
        pago.setBanco(banco);
        
        boolean rpta;
        rpta = ADOPagos.insertarPago(pago, casa);
        if (rpta) {
            //Enviando correo electrónico al usuario
            String msg ="Usted ha registrado el pago Correctamente\n";
            msg+="Referencia Nº: "+pago.getReferencia()+"\n";
            msg+="Monto Nº: "+pago.getMonto()+"\n";
            Gmail.sendMail("edgar_lizcano@hotmail.com", "Registro de Pago", msg);
            
            response.sendRedirect("mispagos.jsp?men=Se registro el Pago de manera correcta");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("mispagos.jsp?men=No se registro el Pago");
        }
    }
    
    private void confirmarPago(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        Usuarios u = (Usuarios) request.getSession().getAttribute("usuario");
        int id = Integer.parseInt(request.getParameter("id"));
        boolean rpta;
        rpta = ADOPagos.confirmarPago(id, u.getIdUsuario());
        Pagos p = ADOPagos.obtenerPagoById(id);
        if (rpta) {
            //Enviando correo electrónico al usuario
            String msg ="Se ha confirmado su pago Correctamente\n";
            msg+="Referencia Nº: "+p.getReferencia()+"\n";
            msg+="Monto: "+p.getMonto()+"\n";
            msg+="Fecha: "+p.getFecha()+"\n";
            msg+="Banco: "+p.getBanco().getNombreBanco()+"\n";
            msg+="Depositante: "+p.getNombreApellido()+" Cedula: "+p.getCedulaDepositante()+ "\n";
            Gmail.sendMail(u.getEmail(), "Confirmación de Pago", msg);
            //Si inserto lo redireccionamos a otra pagina que se llama "result.jsp"
            request.getSession().setAttribute("men", "Se confirmó el pago de manera correcta");
            response.sendRedirect("pagos.jsp");
        } else {
            //Si no se inserto lo redireccionamos a otra pagina que se llama "Usuario.jsp"
            response.sendRedirect("pagos.jsp?men=No se pudo confirmar el pago");
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

    private void pagosPorFiltros(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String radio = request.getParameter("gridRadios");
        String inicio = request.getParameter("fechainicial");
        String fin = request.getParameter("fechafinal");        
        int idCasa = Integer.parseInt(request.getParameter("numCasa"));        
        
        ArrayList <Pagos> lista = null;
        switch (radio) {
                case "todos":
                    lista = ADOPagos.obtenerPagos();
                    break;
                case "pendientes":
                    lista = ADOPagos.obtenerPagosEnEspera();
                    break;
                case "confirmados":
                    lista = ADOPagos.obtenerPagosConfirmados();
                    break;
                case "porFecha":
                    lista = ADOPagos.obtenerPagoByFecha(inicio,fin);
                    break;
                case "porCasa":
                    lista = ADOPagos.obtenerPagoByCasa(idCasa);
                    break;
                default:
                    break;
            }
        if (lista != null) {
            request.getSession().setAttribute("lista", lista);
            response.sendRedirect("pagos.jsp");
        } else {
            request.getSession().setAttribute("men", "No se pudo obtener la lista, consulte al Administrador");
            response.sendRedirect("pagos.jsp");
        }
    }

}

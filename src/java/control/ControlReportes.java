/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import db.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Edgar
 */
public class ControlReportes extends HttpServlet {

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
        metodo1(request, response);
        String accion = request.getParameter("accion");
            switch (accion) {
                case "ReportePagos":
                    this.reportePagos(request, response);
                    break;
                /*case "ObtenerPagosPorHabitante":
                    this.obtenerPagosPorHabitante(request, response);
                    break;
                case "EliminarUsuario":
                    this.eliminarUsuario(request, response);
                    break;
                case "RegistrarVenta":
                    this.registrarVenta(request, response);
                    break;*/
                default:
                    break;
            }
    }
    
    
    protected void metodo1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/pdf");
        System.setProperty("java.awt.headless", "true");
        String path = "D:\\Documents\\NetBeansProjects\\CondominioCardenal\\web\\WEB-INF\\reporte.jasper";
        //String path = "web/WEB-INF/reporte.jasper";
        Connection con = Conexion.getConexion();
        JasperReport reporte = null;
        try {
            ServletOutputStream out = response.getOutputStream();
            
            //reporte = (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath(path));
            reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            System.out.println("Cargó el archivo");
            //int idHab = Integer.parseInt(request.getParameter("idHab"));
            //Map parametro = new HashMap();
            //parametro.put("idHab", Integer.parseInt(idHab));
            
            //ArrayList <CasasHasCuotas> lista = ADOPagos.obtenerPagosByHabitante(idHab);
            
            //JasperPrint jprint = JasperFillManager.fillReport(reporte, null, Conexion.getConexion());
            JasperPrint jprint = JasperFillManager.fillReport(reporte, null, con);
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
            exporter.exportReport();
        } catch (JRException ex) {
            ex.printStackTrace();
            Logger.getLogger(ControlReportes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoClassDefFoundError e){
            e.printStackTrace();
            System.out.println("Error No Class def Found Error: "+e);
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

    private void reportePagos(HttpServletRequest request, HttpServletResponse response) {
        
    }

}

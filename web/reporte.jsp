<%-- 
    Document   : reporte
    Created on : 01-abr-2018, 16:18:09
    Author     : Edgar
--%>

<%@page import="java.sql.Connection"%>
<%@page import="db.Conexion"%>
<%@page import="net.sf.jasperreports.engine.util.JRStyledTextParser"%>
<%@page import="net.sf.jasperreports.engine.*"%>

<%@page import="net.sf.jasperreports.view.JasperViewer"%>

<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //File fileReport = new File(application.getRealPath("reporte.jasper"));
            Connection con = Conexion.getConexion();
            
            File fileReport = new File("D:\\Documents\\NetBeansProjects\\CondominioCardenal\\web\\reporte.jasper");
            
            byte [] bytes = JasperRunManager.runReportToPdf(fileReport.getPath(), null, con);
            
            response.setContentType("application/pdf");
            
            response.setContentLength(bytes.length);
            
            ServletOutputStream outst = response.getOutputStream();
            
            outst.write(bytes,0,bytes.length);
            
            outst.flush();
            
            outst.close();
            
            
        %>
        
        
        <h1>Hello World!</h1>
    </body>
</html>

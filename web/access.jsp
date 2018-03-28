<%@page import="modelo.Modulos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuarios"%>
<%
    response.setHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Cache-Control", "no-store");
    response.addHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    
    HttpSession sesion = request.getSession();
   
    if(sesion.getAttribute("usuario") == null){
        response.sendRedirect("login.jsp");
    }
    /*else{
        ArrayList <Modulos> lista = (ArrayList <Modulos>) sesion.getAttribute("modulos");
        boolean rpta = false;
        for( Modulos m: lista ){
            if (m.getUrlModulo().equals(request.getRequestURI())){
                rpta = true;
            }
        }
        if (rpta){
            response.sendRedirect("index.jsp");
        }
    } */  
%>

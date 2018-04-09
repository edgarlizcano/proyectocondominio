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
    
    String path = request.getRequestURI();
   
    if(sesion.getAttribute("usuario") == null){
        request.getSession().setAttribute("men", "Debe iniciar sesión para acceder al sistema");
        response.sendRedirect("login.jsp");
    }else{
        ArrayList<Modulos> modulos = (ArrayList<Modulos>) session.getAttribute("modulos");
        boolean rpta = false;
        for (Modulos m : modulos) {
            if (path.equals(m.getUrlModulo())){
                rpta = true;
            }
        }
        if (!rpta){
            request.getSession().setAttribute("men", "Usted no tiene permisos suficientes, contacte al Administrador");
            response.sendRedirect("index.jsp");
        }
    }
    
%>

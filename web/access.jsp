<%@page import="modelo.Usuarios"%>
<%
    response.setHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Cache-Control", "no-store");
    response.addHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
    
    if(request.getSession().getAttribute("usuario") == null){
        response.sendRedirect("login.jsp");
    }
%>

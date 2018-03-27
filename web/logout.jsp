<%-- 
    Document   : logout
    Created on : 12-feb-2018, 23:06:00
    Author     : Edgar
--%>

<%@page session = "false" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    request.getSession().removeAttribute("usuario");
    request.getSession().invalidate();
    request.getRequestDispatcher("login.jsp").forward(request, response);
    response.sendRedirect("login.jsp");
%>


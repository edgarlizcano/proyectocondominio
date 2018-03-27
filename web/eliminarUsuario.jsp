<%-- 
    Document   : eliminarUsuario
    Created on : 23-mar-2018, 16:06:20
    Author     : Edgar
--%>

<%@page import="db.ADOUsuarios"%>
<%@page import="modelo.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuarios usuario = new Usuarios();
    usuario.setIdUsuario(Integer.parseInt(request.getParameter("id")));
    boolean accion = ADOUsuarios.eliminarUsuario(usuario);
    if(accion){
        
    }else{
        
    }
%>
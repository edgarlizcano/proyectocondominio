<%-- 
    Document   : menu
    Created on : 18-mar-2018, 22:38:15
    Author     : Edgar
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="db.ADOModulos"%>
<%@page import="modelo.Modulos"%>
<%@page import="modelo.Habitantes"%>
<%@page import="modelo.Habitantes"%>
<%@page import="db.ADOHabitantes"%>
<%@page import="modelo.Usuarios"%>
<%@page import="modelo.Perfiles"%>
<%@page import="db.ADOPerfiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
                <%
                    if(request.getSession().getAttribute("usuario") != null){
                        Usuarios user = (Usuarios) session.getAttribute("usuario");  
                        ArrayList<Modulos> modulos = (ArrayList<Modulos>) session.getAttribute("modulos");
                        for (Modulos m : modulos) {
                            if (m.isVisible()){
                                %>
                                    <li class="nav-item">
                                        <a class="dropdown-item" href="<%= m.getUrlModulo()%>" role="button" aria-haspopup="true" aria-expanded="false"><%= m.getNombreModulo() %></a>
                                    </li>
                                <%
                            }
                        }
                    }
                %>                  
        </ul>
    </div>
</nav>
                
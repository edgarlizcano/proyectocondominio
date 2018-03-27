<%-- 
    Document   : menu
    Created on : 18-mar-2018, 22:38:15
    Author     : Edgar
--%>

<%@page import="modelo.Usuarios"%>
<%@page import="modelo.Perfiles"%>
<%@page import="db.ADOPerfiles"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="col-md-2 d-none d-md-block bg-light sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active" href="index.jsp">
                    <span data-feather="home"></span>
                        Inicio <span class="sr-only">(current)</span>
                </a>
            </li>
                <%
                    if(request.getSession().getAttribute("usuario") != null){
                        Usuarios user = new Usuarios();
                        user = (Usuarios) session.getAttribute("usuario"); 
                        Perfiles perfil = ADOPerfiles.getPerfilByUsuario(user.getNombreUsuario());
                        String accion = perfil.getNombrePerfil();
                        switch (accion) {
                                case "Administrador":
                                    %>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Usuarios</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="usuarios.jsp">Lista de Usuarios</a>
                                              <a class="dropdown-item" href="registrousuario.jsp">Registrar Usuario</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Perfiles</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="perfiles.jsp">Lista de Perfiles</a>
                                              <a class="dropdown-item" href="registroperfil.jsp">Registrar Perfil</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Cuotas</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="cuotas.jsp">Lista de Cuotas</a>
                                              <a class="dropdown-item" href="registrocuota.jsp">Registrar Cuota</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Módulos</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="modulos.jsp">Lista de Módulos</a>
                                              <a class="dropdown-item" href="registromodulo.jsp">Registrar Módulo</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Habitantes</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="habitantes.jsp">Lista de Habitantes</a>
                                              <a class="dropdown-item" href="registrohabitante.jsp">Registrar Habitante</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Pagos</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="pagos.jsp">Lista de Pagos</a>
                                              <a class="dropdown-item" href="registropago.jsp">Registrar Pago</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Casas</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="casas.jsp">Lista de Casas</a>
                                              <a class="dropdown-item" href="registroCasa.jsp">Registrar Casa</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Calles</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="modulos.jsp">Lista de Calles</a>
                                              <a class="dropdown-item" href="registromodulo.jsp">Registrar Calle</a>
                                            </div>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Bancos</a>
                                            <div class="dropdown-menu">
                                              <a class="dropdown-item" href="bancos.jsp">Lista de Bancos</a>
                                              <a class="dropdown-item" href="registrobanco.jsp">Registrar Banco</a>
                                            </div>
                                        </li>
                                    <%
                                    break;
                                case "Usuario":
                                    %>
                                        <li class="nav-item">
                                            <a class="nav-link" href="cuotas.jsp">
                                              <span data-feather="file"></span>
                                              Cuotas
                                            </a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="pagos.jsp">
                                              <span data-feather="file"></span>
                                              Pagos
                                            </a>
                                        </li>
                                    <%
                                    break;
                                default:
                                    break;
                            }
                    }
                %>
        </ul>
    </div>
</nav>
                
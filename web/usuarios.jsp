<%-- 
    Document   : usuarios
    Created on : 23-mar-2018, 13:52:55
    Author     : Edgar
--%>

<%@page import="db.ADOUsuarios"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <%@include file="head.jsp" %>
  <%@include file="access.jsp" %>
    <script> 
        function enviar(){ 
           document.formActualizar.submit(); 
        } 
    </script>
  <body>    
    <%@include file="nav.jsp" %>
    <div class="container-fluid">
      <div class="row">
              <%@include file ="menu.jsp" %>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
              <h1 class="h2">Condominio El Cardenal</h1>
            </div>
            <div class="container-fluid">
                <h1 class="h2">Lista de Usuarios</h1>
                <div>
                    <table class="table">
                        <thead class="thead-dark">
                          <tr>
                            <th scope="col">ID Usuario</th>
                            <th scope="col">Nombre Usuario</th>
                            <th scope="col">Correo</th>
                            <th scope="col">Última Conexión</th>
                            <th scope="col">Estatus</th>
                            <th scope="col" colspan="2">Acciones</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <%-- Lista de todos los Usuarios --%>
                            <%
                                        ArrayList<Usuarios> lista = ADOUsuarios.obtenerUsuarios();
                                        for (Usuarios u : lista) {
                            %>
                            <tr>
                                <td><%= u.getIdUsuario() %></td>
                                <td><%= u.getNombreUsuario() %></td>
                                <td><%= u.getEmail()%></td>
                                <td><%= u.getUltimaConexion() %></td>
                                <%-- Muestra Activo o Inactivo según estado del Usuario --%>
                                <td><%
                                        if (u.getEstatus()){
                                            out.print("Activo");
                                        }else{
                                            out.print("Inactivo");
                                        }
                                    %>
                                </td>
                                <%-- Enlaces a las paginas de Actualizar o eliminar Usuario --%>
                                <td>    
                                    <a class="btn btn-info" role="button" href="actualizarUsuario.jsp?id=<%= u.getIdUsuario() %>">Actualizar</a>
                                    <a class="btn btn-danger" role="button" href="ControladorUsuarios?accion=EliminarUsuario&id=<%= u.getIdUsuario() %>">Eliminar</a>
                                </td>
                            </tr>
                            <%
                                        }
                            %>
                          </tr>
                        </tbody>
                      </table>
                    <%
                        String men=request.getParameter("men");
                        if(men!= null){
                            %>
                                <h1><%=men%></h1>
                            <%
                    }
                    %>
            
        
                </div>
            </div>
            <div class="container-fluid">
                <a class="btn btn-success" role="button" href="registrousuario.jsp">Ingresar Nuevo Usuario</a>
            </div>
        </main>
        </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>

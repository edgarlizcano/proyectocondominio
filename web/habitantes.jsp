<%-- 
    Document   : habitantes
    Created on : 24-mar-2018, 15:41:16
    Author     : Edgar
--%>

<%@page import="db.ADOHabitantes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Habitantes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <%@include file="head.jsp" %>
  <%@include file="access.jsp" %>
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
                <h1 class="h2">Lista de Habitantes</h1>
                    <div>
                        <table class="table">
                            <thead class="thead-dark">
                                <tr>
                                    <th scope="col">ID Habitante</th>
                                    <th scope="col">Cédula</th>
                                    <th scope="col">Nombres</th>
                                    <th scope="col">Apellidos</th>
                                    <th scope="col">Fecha de Nacimiento</th>
                                    <th scope="col">ID Casa</th>
                                    <th scope="col">Nombre Casa</th>
                                    <th scope="col">ID Calle</th>
                                    <th scope="col">Nombre Calle</th>
                                    <th scope="col">Estatus</th>
                                    <th scope="col" colspan="2">Acciones</th>
                                </tr>  
                            </thead>
                            <tbody>
                              <tr>
                                <%-- Lista de todos los Habitantes --%>
                                <%
                                    ArrayList<Habitantes> lista = ADOHabitantes.obtenerHabitantes();
                                    for (Habitantes h : lista) {
                                %>
                                <tr>
                                    <td><%= h.getIdHabitante() %></td>
                                    <td><%= h.getCedula() %></td>
                                    <td><%= h.getNombres() %></td>
                                    <td><%= h.getApellidos() %></td>
                                    <td><%= h.getFechaNacimiento() %></td>
                                    <td><%= h.getCasas().getIdCasas() %></td>
                                    <td><%= h.getCasas().getNombreCasa() %></td>
                                    <td><%= h.getCasas().getCalles().getIdCalles() %></td>
                                    <td><%= h.getCasas().getCalles().getNombreCalle()%></td>
                                    <%-- Muestra Activo o Inactivo según estado del Usuario --%>
                                    <td><%
                                            if (h.isEstatus()){
                                                out.print("Activo");
                                            }else{
                                                out.print("Inactivo");
                                            }
                                        %>
                                    </td>
                                    <%-- Enlaces a las paginas de Actualizar o eliminar Habitante --%>
                                                                       
                                    <td>    
                                        <a class="btn btn-info" role="button" href="actualizarHabitante.jsp?id=<%= h.getIdHabitante() %>">Actualizar</a>
                                        <a class="btn btn-danger" role="button" href="ControladorHabitantes?accion=EliminarHabitante&id=<%= h.getIdHabitante() %>">Eliminar</a>
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
                <a class="btn btn-success" role="button" href="registrohabitante.jsp">Ingresar Nuevo Habitante</a>
            </div>
                    <br>
                    <br>
        </main>
      </div>
    </div>
  </body>
  <%@include file ="scripts.jsp" %>
</html>


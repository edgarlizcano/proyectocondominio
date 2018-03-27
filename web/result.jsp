<%--
    Document   : result
    Created on : 22-mar-2018, 21:42:05
    Author     : Edgar
--%>

<%@page import="modelo.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <%@include file="head.jsp" %>
  <body>
    <nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">El Cardenal</a>
      <%@include file="access.jsp" %>
      <input class="form-control form-control-dark w-100" type="text" placeholder="Search" aria-label="Search">
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <%
                if(request.getSession().getAttribute("usuario") != null){
                    Usuarios user = new Usuarios();
                    user = (Usuarios) session.getAttribute("usuario"); 
                %>
                    <a class="nav-link">Usuario: <%=user.getNombreUsuario()%></a>
                <%
                }
            %>
        </li>  
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="logout.jsp">Sign out</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="index.jsp">
                  <span data-feather="home"></span>
                  Inicio <span class="sr-only">(current)</span>
                </a>
              </li>
              <%@include file ="menu.jsp" %>
            </ul>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
              <h1 class="h2">Condominio El Cardenal</h1>
            </div>
            <h1>Resultado</h1>
                <%
                    String men=request.getParameter("men");
                %>
            <h1><%=men%></h1> 
        </main>
      </div>
    </div>
            <%@include file ="scripts.jsp" %>
  </body>
</html>


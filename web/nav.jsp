<%@page import="modelo.Habitantes"%>
<%@page import="modelo.Usuarios"%>
<%@page import="modelo.Usuarios"%>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">El Cardenal</a>
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

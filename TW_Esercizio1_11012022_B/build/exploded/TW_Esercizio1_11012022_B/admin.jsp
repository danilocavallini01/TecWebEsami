<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>


<%
  Map<HttpSession,Integer> sessioni = ( Map<HttpSession,Integer>)application.getAttribute("sessioni");
%>

<html>
<head>
  <title>Admin</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
    <h1> Admin </h1>
  <table class="formdata">
    <tr>
      <th>
        Sessione
      </th>
      <th>
        Numero
      </th>
    </tr>
    <%
      for ( Map.Entry<HttpSession,Integer> e : sessioni.entrySet() ) {
        System.out.println(new Date().getTime() - e.getKey().getCreationTime());
      if ( (new Date().getTime() - e.getKey().getCreationTime()) < 7 * 24 * 60 * 60 * 1000 ) {
    %>
      <tr>
        <form method="POST" action="./admin.jsp">
          <td>
            <%=e.getKey().getId()%>
          </td>
          <td>
            <%=e.getValue()%>
          </td>
        </form>
      </tr>
    <%
      } }
    %>
  </table>
</div>

</body>
</html>
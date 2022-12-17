<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.Articolo"%>
<%@ page import="java.util.*"%>


<%
    List<Articolo> articoli = (List<Articolo>) this.getServletContext().getAttribute("articoli");
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
        Nome
      </th>
      <th>
        Sessione
      </th>
      <th>
        Revoca
      </th>
    </tr>
    <%
      for ( Articolo a : articoli ) {
      if ( a.getSessionId() != null )
    %>
      <tr>
        <form method="POST" action="./Permissions">
          <td>
            <%=a.getName()%>
          </td>
          <td>
            <%=a.getSessionId()%>
          </td>
          <td class="add-button">
            <input type="hidden" name="name" value=<%=a.getName()%>>
            <input type="hidden" name="session" value=<%=a.getSessionId()%>>
            <input type="submit" name="adminInvalidate" value="Revoca" />
          </td>
        </form>
      </tr>
    <%
      }
    %>
  </table>
</div>

</body>
</html>
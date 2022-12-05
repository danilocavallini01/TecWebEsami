<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Group"%>
<%@ page import="Beans.GroupDb"%>
<%@ page import="Beans.Prodotto"%>
<%@ page import="java.util.*"%>

<%
  User user = (User)session.getAttribute("currentUser");
  GroupDb groups = (GroupDb)application.getAttribute("groups");
%>
<html>
<head>
  <title>Gruppi</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
 <div class="center">
  <h1> Gruppi </h1>
  <p> Ciao <%=user.getUsername()%> del gruppo <%=user.getGroupId()%></p>
  
  <table class="formdata">
    <tr>
      <th>nome</th>
      <th>quantity</th>
    </tr>
      <% for ( Group group : groups.values() ) { %>
        <tr>
          <td>
            <%=group.getProdotto().getNome()%>
          </td>
          <td>
            <%=group.getProdotto().getQuantita() %>
          </td>
        
          <td class="add-button">
            <form method="post" action="Register">
              <input type="hidden" name="id" value=<%=group.getId()%> >
              <input type="submit" name="choose" value="Scegli" />
            </form>
          </td>
        </tr>
      <% } %>
  </table>

  <form action="ticket.jsp" method="post">
    <input type="submit" name="conclude" value="concludi" />
  </form>
</div>

</body>
</html>
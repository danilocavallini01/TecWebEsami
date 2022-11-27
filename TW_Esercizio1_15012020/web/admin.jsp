<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Group"%>
<%@ page import="java.util.*"%>


<html>
<head>
  <title>Admin</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet" />
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <h1> Admin </h1>

   <%
      Map<Integer, Group> groups = (Map<Integer, Group>)this.getServletContext().getAttribute("groups");
      for (Group group : groups.values()) {
    %>
    <h2>Gruppo: <%=group.getId()%></h2>
    <table class="formdata">
      <tr>
        <th>
          Nome Utente
        </th>
        <th>
          Scadenza password in 
        </th>
      </tr>
      <%
        for (User user : group.getUsers()) {
      %>
      <tr>
        <td><%=user.getUsername()%></td>
        <td><%=(user.getLastModifyOnPsw().getTime() - new Date().getTime())/(1000*60*60*24*60) + (60) %> - Giorni</td>
      </tr>
      <% } %>
    </table>
    <% } %>

  </div>

</body>
</html>
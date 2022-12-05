<%@ page session="true"%>

<%@ page import="Beans.GroupDb"%>
<%@ page import="Beans.Group"%>
<%@ page import="Beans.Tickets"%>
<%@ page import="Beans.Ticket"%>
<%@ page import="java.util.*"%>

<%
  GroupDb groups = (GroupDb)application.getAttribute("groups");
  
  if ( request.getParameter("completa") != null) {
    Group group = groups.get(Integer.valueOf(request.getParameter("id")));
    group.getTickets().empty();
    group.setSuccess(3);
  }

  if ( request.getParameter("reset") != null) {
    Group group = groups.get(Integer.valueOf(request.getParameter("id")));
    group.getTickets().empty();
    group.setSuccess(4);
  }
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

  <% for ( Group group : groups.values() ) { %>
  <p> Gruppo <%=group.getId()%> </p>
  <table class="formdata">
    <tr>
      <th>id</th>
      <th>price</th>
      <th>quantity</th>
    </tr>
      <% for ( Ticket t : group.getTickets().getTickets() ) { %>
        <tr>
          <td>
            <%=t.getId()%>
          </td>
          <td>
            <%=t.getPrice()%>
          </td>
          <td>
            <%=t.getQuantity()%>
          </td>
        </tr>
      <% } %>
  </table>
  <form action="admin.jsp" method="post">
    <input type="hidden" name="id" value=<%=group.getId()%> />
    <input type="submit" name="completa" value="completa" />
  </form>
  <form action="admin.jsp" method="post">
    <input type="hidden" name="id" value=<%=group.getId()%> />
    <input type="submit" name="reset" value="reset" />
  </form>
  <% } %>

</body>
</html>
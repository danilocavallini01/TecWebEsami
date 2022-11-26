<%@ page session="true"%>

<%@ page session="true"%>

<%@ page import="Beans.Cart"%>
<%@ page import="Beans.User"%>
<%@ page import="Beans.Ticket"%>
<%@ page import="Beans.Group"%>
<%@ page import="Beans.Catalogue"%>
<%@ page import="java.util.*"%>

<html>
<head>
  <title>Admin  </title>
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
        Group Id
      </th>
      <th>
        Biglietti
      </th>
      <th>
        Resetta
      </th>
      <th>
        Completa
      </th>
    </tr>
    <%
      Map<Integer, Group> groups = (Map<Integer, Group>)this.getServletContext().getAttribute("groups");
      if ( request.getParameter("reset") != null) {
        Integer gid = Integer.valueOf(request.getParameter("id"));
        Group target = groups.get(gid);
        for ( User user : target.getUsers() ) {
          HttpSession sessionUser = user.getSession();
          if ( sessionUser != null ) {
            System.out.println(sessionUser.getId());
            session.setAttribute("success",4);
          }
        }
      }
      
      for (Group group : groups.values()) {
    %>
    <tr>
      <form method="POST" action="./admin.jsp">
        <td>
          <input name="id" value=<%=group.getId()%> />
        </td>
        <td>
          <%=group.getCart().getTickets().size()%>
        </td>
        <td class="add-button">
          <input type="submit" name="reset" value="reset" />
        </td>
        <td class="add-button">
          <input type="submit" name="complete" value="completa" />
        </td>
      </form>
    </tr>
    <% } %>
  </table>

  </div>

</body>
</html>
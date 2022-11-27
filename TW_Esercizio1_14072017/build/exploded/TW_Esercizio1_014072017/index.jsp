<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.Cart"%>
<%@ page import="Beans.User"%>
<%@ page import="Beans.Ticket"%>
<%@ page import="Beans.Group"%>
<%@ page import="Beans.Catalogue"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="catalogue" class="Beans.Catalogue" scope="application" />

<html>
<head>
  <title>Catalogo Biglietti</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>

<%

  Group group = (Group)session.getAttribute("group");
  User user = (User)session.getAttribute("currentUser");
  
  if ( user.getSuccess() != 0 ) {
      this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
  }

  Cart cart = group.getCart();

  if ( request.getParameter("add") != null) {
    int id = Integer.valueOf(request.getParameter("id"));
    for ( Ticket ticket : catalogue.getTickets() ) {
      if ( ticket.getId() == id) {
        cart.addTicket(ticket);
      }
    }
  }

%>
<body>
<div class="center">
  <h1> Catalogo articoli </h1>
  <table class="formdata">
    <tr>
      <th>
        Ticket Id
      </th>
      <th>
        Prezzo
      </th>
    </tr>
    <% for ( Ticket ticket : catalogue.getTickets() ) { %>
      <tr>
        <form method="POST" action="./index.jsp">
          <td>
            <%=ticket.getId()%>
          </td>
          <td>
            <%=ticket.getPrezzo()%>&euro;
          </td>
          <td class="add-button">
            <input type="hidden" name="id" value=<%=ticket.getId()%>>
            <input type="submit" name="add" value="+" />
          </td>
        </form>
      </tr>
    <% } %>
  </table>
  <h1> Biglietti del gruppo </h1>
  <% if ( cart.getTickets().size() > 0 ) { %>
  <table class="formdata">
    <tr>
      <th>
        Ticket Id
      </th>
      <th>
        Prezzo
      </th>
    </tr>
    <% for ( Ticket ticket : cart.getTickets() ) { %>
      <tr>
        <form method="POST" action="./index.jsp">
          <td>
            <%=ticket.getId()%>
          </td>
          <td>
            <input type="hidden" name="id" value=<%=ticket.getId()%>></input>
            <%=ticket.getPrezzo()%>&euro;
          </td>
        </form>
      </tr>
    <% } %>
  </table>
  <% } %>
  <form method="POST" action="./Finalize">
    <input type="submit" name="concludi" value="concludi"></input>
  </form>
</div>

</body>
</html>
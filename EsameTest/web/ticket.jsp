<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Group"%>
<%@ page import="Beans.Tickets"%>
<%@ page import="Beans.Ticket"%>
<%@ page import="java.util.*"%>

<%
  Tickets catalogue = (Tickets)application.getAttribute("catalogue");
  Group userGroup = (Group)session.getAttribute("currentGroup");
  User user = (User)session.getAttribute("currentUser");

  if ( request.getParameter("conclude") != null) {
    user.setConclude(true);

    for ( User other : userGroup.getUsers() ) {
      if ( !other.isConclude() && !user.getUsername().equals(other.getUsername())) {
        userGroup.setSuccess(1);
        application.getRequestDispatcher("/login.jsp").forward(request, response);
        return;
      }
    }

    userGroup.setSuccess(2);
    for ( User other : userGroup.getUsers() ) {
       user.setConclude(false);
    }
    userGroup.getTickets().empty();
    application.getRequestDispatcher("/login.jsp").forward(request, response);
    return;
  }

  if ( userGroup.getSuccess() > 2 ) {
    application.getRequestDispatcher("/login.jsp").forward(request, response);
    return;
  }

  if ( request.getParameter("add") != null) {
    int id = Integer.valueOf(request.getParameter("id"));

    for ( Ticket t : catalogue.getTickets() ) {
      if (t.getId() == id ) {
        if ( t.getQuantity() >= 1 ) {
          t.setQuantity(t.getQuantity() - 1);

          if ( userGroup.getTickets().get(id) != null ) {
            Ticket target = userGroup.getTickets().get(id);
            target.setQuantity(target.getQuantity() + 1);
          } else {
            userGroup.getTickets().addTicket(new Ticket(id,t.getPrice(),1));
          }
          System.out.println(userGroup.getTickets());
          break;
        }
      }
    }
   
  }
%>
<html>
<head>
  <title>Cart</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
 <div class="center">
  <h1> Cart </h1>
  <p> Ciao <%=user.getUsername()%> del gruppo <%=user.getGroupId()%></p>
  <p> Catalogo </p>
  <table class="formdata">
    <tr>
      <th>id</th>
      <th>price</th>
      <th>quantity</th>
    </tr>
      <% for ( Ticket t : catalogue.getTickets() ) { %>
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
        
          <td class="add-button">
            <form method="post" action="ticket.jsp">
              <input type="hidden" name="id" value=<%=t.getId()%> >
              <input type="submit" name="add" value="+" />
            </form>
          </td>
        </tr>
      <% } %>
  </table>

  <p> Biglietti del tuo gruppo </p>
  <table class="formdata">
    <tr>
      <th>id</th>
      <th>price</th>
      <th>quantity</th>
    </tr>
      <% for ( Ticket t : userGroup.getTickets().getTickets() ) { %>
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
  <form action="ticket.jsp" method="post">
    <input type="submit" name="conclude" value="concludi" />
  </form>
</div>

</body>
</html>
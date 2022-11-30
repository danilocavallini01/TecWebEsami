<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.Prenotazioni"%>

<%
List<Prenotazioni> prenotazioni = (List<Prenotazioni>)application.getAttribute("prenotazioni");
%>
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
  <table class="formdata">
    <tr>
      <th>id</th>
      <th>check-in</th>
      <th>check-out</th>
      <th>price</th>
    </tr>
      <% for ( Prenotazioni pre : prenotazioni ) {
        if ( pre.isFinalized()) {
      %>
      <tr>
        <td>
          <%=pre.getId()%>
        </td>
        <td>
          <%=pre.getCheckin()%>
        </td>
        <td>
          <%=pre.getCheckout()%>
        </td>
        <td>
          <%=pre.getPrice()%>
        </td>
      </tr>
      <%
        }
      }
      %>
  </table>

</div>

</body>
</html>
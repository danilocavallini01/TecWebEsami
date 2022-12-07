<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Prenotazione"%>
<%@ page import="java.util.*"%>


<%

  List<Prenotazione> prenotazioni = (List<Prenotazione>)application.getAttribute("prenotazioni");

  if ( request.getParameter("cancel") != null ) {
    int campo = Integer.parseInt(request.getParameter("campo"));
    int giorno = Integer.parseInt(request.getParameter("giorno"));
    int orario = Integer.parseInt(request.getParameter("orario"));
    User user = (User)session.getAttribute("currentUser");

    Prenotazione p = null;

    for ( Prenotazione prenotazione : prenotazioni ) {
        if ( prenotazione.getCampo() == campo && prenotazione.getGiorno() == giorno ) {
            p = prenotazione;
            break;
        }
    }

    if ( p != null ) {
        prenotazioni.remove(p);
    }
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
  <table class="formdata">
    <tr>
      <th>
        Campo
      </th>
      <th>
        Giorno
      </th>
      <th>
        Orario
      </th>
    </tr>
    <%
      for ( Prenotazione p : prenotazioni ) {
    %>
      <tr>
        <form method="POST" action="./admin.jsp">
          <td>
            <%=p.getCampo()%>
          </td>
          <td>
            <%=p.getGiorno()%>
          </td>
          <td>
            <%=p.getOrario()%>
          </td>
          <td class="add-button">
            <input type="hidden" name="campo" value=<%=p.getCampo()%>>
            <input type="hidden" name="giorno" value=<%=p.getGiorno()%>>
            <input type="hidden" name="orario" value=<%=p.getOrario()%>>
            <input type="submit" name="cancel" value="Annulla" />
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
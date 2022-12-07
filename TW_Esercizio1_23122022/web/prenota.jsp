<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="Beans.User"%>
<%@ page import="Beans.Prenotazione"%>
<%@ page import="java.util.*"%>


<%
  User user = (User)session.getAttribute("currentUser");
  List<Prenotazione> prenotazioni = (List<Prenotazione>)application.getAttribute("prenotazioni");

  if ( user.getAttribute("success") != null ) {
    this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
  }
%>

<html>
<head>
  <title>Prenotazioni</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center" >
  <div class="center">
    <h1> Prenota </h1>
    <label for="campo">
      campo
      <input id="campo" name="campo" type="text" size="30" onkeyup="checkInput()"/>
    </label>
    
    <label for="giorno">
      giorno
      <input id="giorno" name="giorno" type="text" size="30" onkeyup="checkInput()"/>
    </label>
  
    <label for="orario">
      orario di inizio
      <input id="orario" name="orario" type="text" size="30" onkeyup="checkInput()" />
    </label>
  
    <p id="result"></p>
  </div>
  
  <div class="center">
    <h1> Prenotazioni eseguite </h1>
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
          <form method="POST" action="./Acquista">
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
              <input type="submit" name="add" value="Scegli" />
            </td>
          </form>
        </tr>
      <%
        }
      %>
    </table>
  </div>
</div>


</body>
</html>
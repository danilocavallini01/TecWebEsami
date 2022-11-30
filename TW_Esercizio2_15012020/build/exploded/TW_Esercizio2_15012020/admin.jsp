<%@ page session="true"%>

<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.*"%>

<%
  if ( request.getParameter("block") != null) {
    List<HttpSession> sessions = (List<HttpSession>)this.getServletContext().getAttribute("sessions");
    for ( HttpSession sessionUser: sessions ) {
      sessionUser.invalidate();
    }

    this.getServletContext().setAttribute("sessions", sessions);
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
<form class="center" action="admin.jsp" method="GET" >
  <h1> Admin </h1>
  
  <% 
    List<HttpSession> sessions = (List<HttpSession>)this.getServletContext().getAttribute("sessions");
    
    int operazioniTotali = 0;
    for ( HttpSession sessionUser : sessions ) {
      operazioniTotali += (int)sessionUser.getAttribute("operazioni");
    %>
    <p> ID: <%=sessionUser.getId()%> operazioni: <%=(int)sessionUser.getAttribute("operazioni")%></p>
    <%
    }
  %>

  <% if ( sessions.size() >= 10 && operazioniTotali >= 1000) { %>
    <p> Blocca utenti</p>
    <input type="submit" name="block" value="blocca"/>
    <p id="result"></p>
  <%
    }
  %>
</form>

</body>
</html>
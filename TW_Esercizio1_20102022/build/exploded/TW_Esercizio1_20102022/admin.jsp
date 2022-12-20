<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.Tavolo"%>
<%@ page import="Beans.Drink"%>

<%
	List<Tavolo> tables = (List<Tavolo>)this.getServletContext().getAttribute("tables");

	if ( request.getParameter("end") != null ) {
		application.setAttribute("serviceEnded", true);
		for ( Tavolo t : tables) {
			int totalPrice = 0;

			for ( Drink d : t.getDrinks() ) {
				totalPrice += d.getPrice();
			}

			if ( totalPrice < 100 ) {
				totalPrice = 100;
			}

			t.setTotalPrice(totalPrice);
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
		<h1>Admin</h1>
		<p> Termina Servizio </p>
		<form method="POST" action="admin.jsp">
            <input type="submit" name="end" value="Termina">
        </form>

		<% for ( Tavolo t: tables ) { %>
			<h1> Tavolo <%=t.getNumber()%></h1>
			<table class="formdata">
				<tr>
				<th>
					Drink
				</th>
				<th>
					Consegnato
				</th>
				</tr>
				<%
				for ( Drink d : t.getDrinks()) {
				%>
				<tr>
					<td>
					<%=d.getName()%>
					</td>
					<td>
					<%=d.isDelivered()%>
					</td>
				</tr>
				<%
				}
				%>
			</table>
		<%
			}
		%>
	</div>

</body>
</html>
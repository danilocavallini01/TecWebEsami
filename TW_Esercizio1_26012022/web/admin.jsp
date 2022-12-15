<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.TotalRequest" %>

<%
List<HttpSession> sessions = (List<HttpSession>) application.getAttribute("sessions");
TotalRequest tr = (TotalRequest)application.getAttribute("totalRequest");
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
		<h2>Totale Operazioni</h1>
		<p><%=tr.getOperations()%></p>
		<table class="formdata">
			<tr>
				<th>Sessione</th>
				<th>Numero</th>
			</tr>
			<%
			for (HttpSession ses : sessions) {
				if (session.getAttribute("valid") != null) {
			%>
			<tr>
				<form method="POST" action="./admin.jsp">
					<td><%=ses.getId()%></td>
					<td><%=ses.getAttribute("operation")%></td>
				</form>
			</tr>
			<%
				}
			}
			%>
		</table>
	</div>

</body>
</html>
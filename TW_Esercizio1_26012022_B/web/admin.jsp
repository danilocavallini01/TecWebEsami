<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="java.util.*"%>
<%@ page import="Beans.ArticoloAsta"%>
<%@ page import="Beans.User"%>

<%
	List<ArticoloAsta> asta = ( List<ArticoloAsta>)application.getAttribute("asta");
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
		<h1> Aste ancora attive</h1>
		<table class="formdata">
			<tr>
			  <th>
				Nome
			  </th>
			  <th>
				Numero offerte
			  </th>
			</tr>
			<%
			  for ( ArticoloAsta a : asta) {
				if ( !a.isConclusa()) {
			%>
			  <tr>
				<td>
				  <%=a.getName()%>
				</td>
				<td>
				  <%=a.getOffers()%>
				</td>
			  </tr>
			<%
			  }}
			%>
		</table>
		<h1> Utenti Vincitori</h1>
		<table class="formdata">
			<tr>
			  <th>
				Nome
			  </th>
			</tr>
			<%
			  for ( ArticoloAsta a : asta) {
				if ( a.isConclusa()) {
			%>
			  <tr>
				<td>
				  <%=a.getWinner().getUsername()%>
				</td>
			  </tr>
			<%
			  }}
			%>
		</table>
	</div>

</body>
</html>
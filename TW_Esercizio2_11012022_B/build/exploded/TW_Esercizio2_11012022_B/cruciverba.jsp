<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%
    char [][] cruciverba = (char [][])this.getServletContext().getAttribute("cruciverba");
%>
<html>
<head>
  <title>Login</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div>
<div class="center">
    <h1> Cruciverba </h1>

    <% for ( int i = 0; i < 10; i++) { %>
        <div>
            <% for ( int j = 0; j < 10; j++ ) { %>
                <input type="text" row="<%=i%>" col="<%=j%>" size="1" onchange="addCruciverbaChar(this)" value="<%=cruciverba[i][j] == '0' ? '\s' : cruciverba[i][j]%>"/>
            <% }%>
        </div>
    <% }%>
    <p id="result"></p>
</div>
</body>
</html>
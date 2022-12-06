<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="com.google.gson.Gson"%>

<html>
<head>
  <title>TextRandom</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>

<div class="center">

  <form class="center" method="POST" action="RandomizeServlet">
    <h1> Casual  </h1>
    <div>
      <label id="text" for="content">Testo casuale</label>
      <textarea id="content" rows="3" onkeyup="sendText()"></textarea>
    </div>

    <input id="textJson" name="text" type="hidden" />
    <input class="hide" id="textSubmit" type="submit" value="Invia"/>
    <% if (session.getAttribute("result") != null) { %>
      <p id="result"><%=new Gson().fromJson((String)session.getAttribute("result"), Integer.class)%></p>
    <%}%>
  </form>

  <form class="center" method="POST" action="Login">
      <h1> Log in </h1>
      <label for="start">
        Email
        <input id="user" name="username" type="text" size="30" />
      </label>
      
      <label for="end">
        Password
        <input id="psw" name="password" type="password" size="30" />
      </label>

      <input type="submit" value="Login"/>
  </form>
</div>

</body>
</html>
<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

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
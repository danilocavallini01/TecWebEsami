<%@ page session="true"%>

<html>
<head>
  <title>Scegli Tavolo</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div class="center">
  <form class="center" method="POST" action="Choose">
    <h1> Scegli Tavolo </h1>
    <label for="table">
      Numero tavolo
      <input id="table" name="table" type="text" size="30" />
    </label>
  
    <input type="submit" value="Scegli"/>
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
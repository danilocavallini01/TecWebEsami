<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<html>
<head>
  <title>Libro</title>
  <link type="text/css" href="styles/default.css" rel="stylesheet">
  </link>
  <script type="text/javascript" src="scripts/utils.js"></script>
  <script type="text/javascript" src="scripts/script.js"></script>
</head>
<body>
<div>
<div class="center">
  <div class="center">
    <h1> Log in </h1>
    <label for="start">
      Numero del capitolo
      <input id="cap" name="cap" type="text" size="30" />
    </label>
    <input type="button" value="Seleziona" onclick="chooseChapter()"/>
    <p id="result_cap"></p>
  </div>

  <div class="center">
    <label id="text" for="content">Contenuto del capitolo</label>
    <textarea id="content" rows="3"></textarea>
    <p id="result"></p>
    <input type="button" value="Salva Contenuto" onclick="saveChapter()"/>
  </div>
</div>
</body>
</html>
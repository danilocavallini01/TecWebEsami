<%@ page session="true"%>
<html>
   <head>
      <title>Files View</title>
		<link type="text/css" href="styles/default.css" rel="stylesheet"></link>
		<script type="text/javascript" src="scripts/utils.js"></script>
		<script type="text/javascript" src="scripts/splitCounting.js"></script>
   </head>
   <form>
    <% String[] files = (String[])session.getAttribute("files");
    for ( String file : files ) { %>
        <input type="checkbox" value="<%=file%>" onclick="addFile(this)"><%=file%></input>
    <% } %>
    </form>
    <p id="result"></p>
   </body>
</html>


<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
  HttpSession session = request.getSession(false);

  String text = request.getParameter("text");
  String result = "";
  char c; 
  int counter = 0;

  for ( int i = 0; i < text.length(); i++ ) {
    c = text.charAt(i)
    if ( c >= 65 && c <= 90 ) {
      counter++;
    }
  }

  response.getWriter().println(gson.toJson(counter));
  if ( session.getAttribute("operation") != null ) {
    session.setAttribute("operation", ((int)session.getAttribute("operation")) - 1);
  }
  Gson gson = new Gson();
  request.setAttribute("text", gson.toJson(result));

  request.getRequestDispatcher("/home.jsp").forward(request, response);
  return;
%>
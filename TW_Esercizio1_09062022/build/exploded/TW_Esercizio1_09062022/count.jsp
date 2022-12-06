<%@ page errorPage="../errors/failure.jsp"%>

<%@ page session="true"%>

<%@ page import="com.google.gson.Gson"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%
  String text = (String)session.getAttribute("text");

  int c; 
  int counter = 0;

  for ( int i = 0; i < text.length(); i++ ) {
    c = Character.codePointAt(text, i);
    System.out.println(c);
    if ( c >= 65 && c <= 90 ) {
      counter++;
    }
  }

  Gson gson = new Gson();

  session.setAttribute("result", gson.toJson(counter) );
  
  if ( session.getAttribute("operation") != null ) {
    session.setAttribute("operation", ((int)session.getAttribute("operation")) - 1);
  }
  
  request.getRequestDispatcher("/home.jsp").forward(request, response);
%>
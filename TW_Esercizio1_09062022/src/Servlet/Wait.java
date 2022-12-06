package Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Wait extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if ( this.getServletContext().getAttribute("totalOperation") == null ) {
            this.getServletContext().setAttribute("totalOperation", 0);
        }
    }

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().println("<p> Server sovraccarico: la richiesta viene rallentata di 10 secondi </p>");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new ServletException(e.getMessage());
        }
        this.getServletContext().getRequestDispatcher("/RandomizeServlet").forward(request, response);
    }
}

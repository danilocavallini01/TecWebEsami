package Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;


public class RandomizeServlet extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("totalOperation") == null ) {
            this.getServletContext().setAttribute("totalOperation", 0);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        this.getServletContext().setAttribute("totalOperation", (int)this.getServletContext().getAttribute("totalOperation") + 1);

        if ( session.getAttribute("operation") == null ) {
            session.setAttribute("operation", 1);
        } else if ((int)session.getAttribute("operation") >= 3 ) {
            this.getServletContext().getRequestDispatcher("/Wait").forward(request, response);
            return;
        }

        String text = gson.fromJson(request.getParameter("text"), String.class);
        String result = "";
        int randomNumber = (int)(Math.random() * 60d % 10);

        for ( int i = 0; i < text.length(); i++ ) {
            if ( i % randomNumber != 0) {
                result += text.charAt(i);
            }
        }

        request.setAttribute("text", result);
        this.getServletContext().getRequestDispatcher("/count.jsp").forward(request, response);
    }
}
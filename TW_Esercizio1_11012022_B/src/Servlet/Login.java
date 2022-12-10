package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("sessioni") == null ) {
            Map<HttpSession,Integer> sessioni = new HashMap<HttpSession,Integer>();
            this.getServletContext().setAttribute("sessioni", sessioni );
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || password == null) {
            this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
            return;
        }

        if (username.equals("admin") && password.equals("admin")) {

            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
            return;
        }
    }
}
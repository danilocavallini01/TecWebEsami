package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.Articolo;

public class AdminLogin extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if (this.getServletContext().getAttribute("articoli") == null) {
            List<Articolo> articoli = new ArrayList<Articolo>();

            this.getServletContext().setAttribute("articoli", articoli);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ( username.equals("admin") && password.equals("admin") ) {
            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        }
    }
}
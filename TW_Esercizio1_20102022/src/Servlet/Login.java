package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Drink;
import Beans.Tavolo;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("drinks") == null) {
            
            List<Drink> drinks = new ArrayList<Drink>();
            drinks.add(new Drink("mojito", 7));
            drinks.add(new Drink("sexOnTheBeach", 6));
            drinks.add(new Drink("caipiroska", 5));
            drinks.add(new Drink("amaro", 3));
            drinks.add(new Drink("blackRussian", 5));


            List<Tavolo> tables = new ArrayList<Tavolo>();
            for ( int i = 0; i < 5; i++ ) {
                tables.add(new Tavolo(i + 1));
            }

            this.getServletContext().setAttribute("drinks", drinks);
            this.getServletContext().setAttribute("tables", tables);
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
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }
}
package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Drink;
import Beans.Tavolo;



public class Choose extends HttpServlet {
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
            this.getServletContext().setAttribute("serviceEnded", false);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	List<Tavolo> tables = ( List<Tavolo>)this.getServletContext().getAttribute("tables");
        HttpSession session = request.getSession(false);
        
        int table = Integer.parseInt(request.getParameter("table"));

        if ( session.getAttribute("table") != null ) {
            this.getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
            return;
        }

        Tavolo found = null;

        for ( Tavolo t : tables ) {
            if (t.getNumber() == table) {
                found = t;
                break;
            }
        } 

        if ( found != null ) {
           found.getUsers().add(session);
           session.setAttribute("table", found);
           this.getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
           return;
        }

        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        return;
    }
}
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

import Beans.Cameriere;
import Beans.Drink;
import Beans.Tavolo;

public class OrderDrink extends HttpServlet {
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
            for (int i = 0; i < 5; i++) {
                tables.add(new Tavolo(i + 1));
            }

            this.getServletContext().setAttribute("drinks", drinks);
            this.getServletContext().setAttribute("tables", tables);
            this.getServletContext().setAttribute("serviceEnded", false);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Drink> drinks = ( List<Drink>)this.getServletContext().getAttribute("drinks");

        if ( (boolean)this.getServletContext().getAttribute("serviceEnded") ) {
            this.getServletContext().getRequestDispatcher("/review.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(false);

        Tavolo table = (Tavolo)session.getAttribute("table");
        String name = request.getParameter("name");

        Drink found = null;

        for ( Drink d : drinks) {
            if ( d.getName().equals(name)) {
                found = d;
                break;
            }
        }

        if ( found == null ) {
            this.getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
            return;
        }

        Drink clone = new Drink(found.getName(), found.getPrice());

        table.getDrinks().add(clone);
        List<Drink> ordered = (List<Drink>)session.getAttribute("drinks");

        if ( ordered == null ) {
            ordered = new ArrayList<Drink>();
            session.setAttribute("drinks", ordered);
        } 

        ordered.add(clone);
        Cameriere c = new Cameriere(clone);
        c.start();
        
        this.getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
    }
}
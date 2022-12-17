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

public class Choose extends HttpServlet {
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

        List<Articolo> articoli = (List<Articolo>) this.getServletContext().getAttribute("articoli");

        String art = request.getParameter("art");

        for ( Articolo a : articoli ) {
            if ( a.getName().equals(art) ) {

                response.getWriter().println(gson.toJson(a));
                return;
            }
        }

        Articolo a = new Articolo("", art);
        articoli.add(a);

        response.getWriter().println(gson.toJson(a));
    }
}
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

import Beans.Articolo;

public class ModArticolo extends HttpServlet {
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
        HttpSession session = request.getSession();

        String articolName = request.getParameter("name");
        
        Articolo found = null;

        for (Articolo a : articoli) {
            if (a.getName().equals(articolName)) {
                found = a;
                break;
            }
        }

        if (request.getParameter("get") != null) {

            if (found == null) {
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                return;
            }

            if (found.getSessionId() == null) {
                found.setSessionId(session.getId());
                session.setAttribute("articolo", found);
            }
        } else if ( request.getParameter("write") != null) {
            found = (Articolo)session.getAttribute("articolo");

            if ( !found.getSessionId().equals(session.getId()) ) {
                session.removeAttribute("articolo");
                this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
                return;
            }

            found.setContent(request.getParameter("content"));
        } else {
            if (request.getParameter("adminInvalidate") != null) {

                if (found == null) {
                    this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
                    return;
                }

                found.setSessionId(null);
                        
                this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
                return;
            } else {
                found = (Articolo) session.getAttribute("articolo");
                found.setSessionId(null);
                session.removeAttribute("articolo");
            }
        }

        
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
        return;
    }
}
package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Prenotazione;
import Beans.User;
import Beans.UserDb;

public class Acquista extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("users") == null
                || this.getServletContext().getAttribute("groups") == null) {
            UserDb users = new UserDb();
            List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();

            User user = new User("a", "a");
            User usr1 = new User("b", "b");
            User usr2 = new User("c", "c");
            User usr3 = new User("d", "d");
            User admin = new User("admin", "admin");
    
            users.add(user); 
            users.add(usr1);
            users.add(usr2);
            users.add(usr3);
            users.add(admin);

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("prenotazioni", prenotazioni);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
       
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("currentUser");

        List<Prenotazione> prenotazioni = (List<Prenotazione>)this.getServletContext().getAttribute("prenotazioni");

        int campo = Integer.parseInt(request.getParameter("campo"));
        int giorno = Integer.parseInt(request.getParameter("giorno"));
        int orario = Integer.parseInt(request.getParameter("orario"));


        if ( request.getParameter("add") == null ) {
            for ( Prenotazione prenotazione : prenotazioni ) {
                if ( prenotazione.getCampo() == campo && prenotazione.getGiorno() == giorno  ) {
                    this.showFivePossibilities(response, giorno, prenotazioni);
                    return;
                }
            }
    
            Prenotazione p = new Prenotazione(campo, giorno, orario);
            p.setTemp(true);
            p.setDate(new Date());
            p.setUser1(user);
            prenotazioni.add(p);
    
            session.setAttribute("prenotazione", p);
            response.getWriter().println(gson.toJson("ok"));
        } else {
            Prenotazione p = null;

            for ( Prenotazione prenotazione : prenotazioni ) {
                if ( prenotazione.getCampo() == campo && prenotazione.getGiorno() == giorno ) {
                    p = prenotazione;
                    break;
                }
            }

            if ( p == null ) {
                this.getServletContext().getRequestDispatcher("/prenota.jsp").forward(request, response);
                return;
            }

            if ( (p.getDate().getTime() - new Date().getTime()) > 2 * 60 * 60 * 1000 ) {
                prenotazioni.remove(p);
                this.getServletContext().getRequestDispatcher("/prenota.jsp").forward(request, response);
                return;
            }

            p.setUser2(user);
            p.setTemp(false);

            p.getUser1().getSession().setAttribute("success", true);
            session.setAttribute("success", true);

            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    private void showFivePossibilities(HttpServletResponse response, int giorno, List<Prenotazione> prenotazioni) throws IOException {
        Integer [] possibilities = new Integer[5];
        List<Integer> available = new ArrayList<Integer>();

        for ( int i = 1; i < 6; i++ ) {
            possibilities[i - 1] = i;
        }

        for ( Prenotazione prenotazione : prenotazioni ) {
            if ( prenotazione.getGiorno() == giorno ) {
                possibilities[prenotazione.getCampo() - 1] = 0;
            }
        }

        for ( Integer campo : possibilities ) {
            if ( campo != 0 ) {
                available.add(campo);
            }
        }

        response.getWriter().println(available);
    }
}
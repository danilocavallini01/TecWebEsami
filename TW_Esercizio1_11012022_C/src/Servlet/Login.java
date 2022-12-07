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

import Beans.Prenotazione;
import Beans.User;
import Beans.UserDb;

public class Login extends HttpServlet {
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDb users = (UserDb)this.getServletContext().getAttribute("users");
      
        User user = users.get(username);
        HttpSession session = request.getSession();

        if (username == null || user == null) {
            session.setAttribute("error", "username non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (username.equals("admin") && user.getPassword().equals(password)) {

            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
            return;
        } else if (user.getPassword().equals(password)) {
            
            session.setAttribute("currentUser", user);
            session.removeAttribute("error");

            user.setSession(session);
            this.getServletContext().getRequestDispatcher("/prenota.jsp").forward(request, response);
            return;
        } else {
            session.setAttribute("error", "psw o user errati");

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }
}
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

import Beans.ArticoloAsta;
import Beans.User;
import Beans.UserDb;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        
        if (this.getServletContext().getAttribute("users") == null) {
            UserDb users = new UserDb();
            List<ArticoloAsta> asta = new ArrayList<ArticoloAsta>();
            Random random = new Random(System.currentTimeMillis());
           
            asta.add(new ArticoloAsta("Fucile", new Date(random.nextInt(360000) + (60 * 1000) + System.currentTimeMillis())));
            asta.add(new ArticoloAsta("Padella", new Date(random.nextInt(360000) + (60 * 1000) + System.currentTimeMillis())));
            asta.add(new ArticoloAsta("Casa", new Date(random.nextInt(360000) + (60 * 1000) + System.currentTimeMillis())));
            asta.add(new ArticoloAsta("Macchina", new Date(random.nextInt(360000) + (60 * 1000) + System.currentTimeMillis())));
            asta.add(new ArticoloAsta("PC", new Date(random.nextInt(360000) + (60 * 1000) + System.currentTimeMillis())));

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
            this.getServletContext().setAttribute("asta", asta);
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
            session.setAttribute("user", user);
            this.getServletContext().getRequestDispatcher("/asta.jsp").forward(request, response);
            return;
        } else {

            session.setAttribute("error", "psw o user errati");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }
}
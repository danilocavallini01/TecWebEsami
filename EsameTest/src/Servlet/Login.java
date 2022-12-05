package Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Group;
import Beans.GroupDb;
import Beans.Ticket;
import Beans.Tickets;
import Beans.User;
import Beans.UserDb;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("users") == null ) {
            UserDb users = new UserDb();
            GroupDb groups = new GroupDb();
    
            Group group1 = new Group(1);
            Group group2 = new Group(2);
    
            User user = new User("a", "a", group1.getId());
            User usr1 = new User("b", "b", group1.getId());
            User usr2 = new User("c", "c", group2.getId());
            User usr3 = new User("d", "d", group2.getId());
            User admin = new User("admin", "admin");
    
            users.add(user); 
            users.add(usr1);
            users.add(usr2);
            users.add(usr3);
            users.add(admin);
    
            group1.addUser(user, usr1);
            group2.addUser(usr2, usr3);
            
            groups.add(group1);
            groups.add(group2);
    
            Tickets catalogue = new Tickets();
    
            for ( int i = 0; i < 6; i++ ) {
                catalogue.addTicket(new Ticket(i, (float)(Math.random() * 10), (int)(Math.random() * 4) + 1));
            }
    
            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("groups", groups);
            this.getServletContext().setAttribute("catalogue", catalogue);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDb users = (UserDb)this.getServletContext().getAttribute("users");
        GroupDb groups = (GroupDb)this.getServletContext().getAttribute("groups");

        User user = users.get(username);
        HttpSession session = request.getSession();

        if (username == null || user == null) {
            session.setAttribute("error", "username non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (username.equals("admin") && user.getPassword().equals(password)) {

            session.setAttribute("currentUser", user);
            session.removeAttribute("currentGroup");
            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
            return;
        } else if (user.getPassword().equals(password)) {
            
             
            session.setAttribute("currentUser", user);
            session.setAttribute("currentGroup", groups.get(user.getGroupId()));
            session.removeAttribute("error");
            user.setSession(session);

            this.getServletContext().getRequestDispatcher("/ticket.jsp").forward(request, response);
            return;
        } else {
            session.setAttribute("error", "psw o user errati");
            this.getServletContext().setAttribute("users", users);
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }
}
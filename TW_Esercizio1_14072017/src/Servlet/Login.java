package Servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hsqldb.types.Collation;

import com.google.gson.Gson;

import Beans.Catalogue;
import Beans.Group;
import Beans.Ticket;
import Beans.User;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        Map<String, User> registeredUsers = new HashMap<String, User>();
        Map<Integer, Group> groups = new HashMap<Integer, Group>();

        Group group1 = new Group(1);
        Group group2 = new Group(2);

        User user = new User("a", "a", group1.getId());
        User usr1 = new User("b", "b", group1.getId());
        User usr2 = new User("c", "c", group2.getId());
        User usr3 = new User("d", "d", group2.getId());
        User admin = new User("admin", "admin");

        registeredUsers.put(user.getUsername(), user); 
        registeredUsers.put(usr1.getUsername(), usr1);
        registeredUsers.put(usr2.getUsername(), usr2);
        registeredUsers.put(usr3.getUsername(), usr3);
        registeredUsers.put(admin.getUsername(), admin);

        group1.addUser(user, usr1);
        group2.addUser(usr2, usr3);
        
        groups.put(group1.getId(),group1);
        groups.put(group2.getId(),group2);

        Catalogue catalogue = new Catalogue();

        for ( int i = 0; i < 6; i++ ) {
            catalogue.addTicket(new Ticket(i, (float)(Math.random() * 10)));
        }

        this.getServletContext().setAttribute("registeredUsers", registeredUsers);
        this.getServletContext().setAttribute("groups", groups);
        this.getServletContext().setAttribute("catalogue", catalogue);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, User> registeredUsers = (Map<String,User>)this.getServletContext().getAttribute("registeredUsers");

        User user = registeredUsers.get(username);
        HttpSession session = request.getSession();

        if ( username == null ) {
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        session.setAttribute("currentUser", user);
        if ( username.equals("admin") && user.getPassword().equals(password) ) {
            
            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);

        } else if ( user.getPassword().equals(password) ) {

            Map<Integer, Group> groups = (Map<Integer, Group>)this.getServletContext().getAttribute("groups");
           
            Group group = groups.get(user.getGroupId());

            session.setAttribute("group", group);
            
            user.setSession(session);
            System.out.println(session.getId());
            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {

            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
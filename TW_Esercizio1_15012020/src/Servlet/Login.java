package Servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Group;
import Beans.GroupDb;
import Beans.User;
import Beans.UserDb;

public class Login extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if (this.getServletContext().getAttribute("users") == null
                || this.getServletContext().getAttribute("groups") == null) {
            GroupDb groups = new GroupDb();
            UserDb users = new UserDb();

            Group group1 = new Group(1);
            Group group2 = new Group(2);
            User admin = new User("admin", "admin");

            groups.add(group1);
            groups.add(group2);

            users.add(admin);

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("groups", groups);
        }

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        GroupDb groups = (GroupDb)this.getServletContext().getAttribute("groups");
        UserDb users = (UserDb)this.getServletContext().getAttribute("users");

        User user = users.get(username);
        HttpSession session = request.getSession();

        if (username == null) {
            session.setAttribute("error", "username non esistente");
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        if (username.equals("admin") && user.getPassword().equals(password)) {

            session.setAttribute("currentUser", user);
            this.getServletContext().getRequestDispatcher("/admin.jsp").forward(request, response);
            return;
        } else if (user.getPassword().equals(password) && !user.isTooManyTryies()) {

            Group group = groups.get(user.getGroupId());

            if ((new Date().getTime() - user.getLastModifyOnPsw().getTime()) < 1000 * 60 * 60 * 24 * 60) {
                user.setValid(false);

                int countInvalid = 0;
                for (User gUser : group.getUsers()) {
                    if (!gUser.isValid()) {
                        countInvalid++;
                    }
                }

                if (countInvalid >= 3) {
                    for (User gUser : group.getUsers()) {
                        gUser.setValid(false);
                    }
                }

                user.setTryiedPsw(0);
                session.setAttribute("currentUser", user);
                session.removeAttribute("error");
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            } else {
                users.remove(username);

                this.getServletContext().setAttribute("users", users);
                session.setAttribute("error", "la psw e' scaduta reimpostarla");
                this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        } else {

            user.setTryiedPsw(user.getTryiedPsw() + 1);
            session.setAttribute("error", "psw sbagliata");

            if (user.getTryiedPsw() >= 3) {
                session.setAttribute("error", "hai provato la password troppe volte !");
                user.setTooManyTryies(true);
            }

            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
    }
}
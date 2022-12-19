package Servlet;

import java.io.File;
import java.io.FileReader;
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
import Beans.Result;
import Beans.User;
import Beans.UserDb;

public class Purchase extends HttpServlet {
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

            users.add(user);
            users.add(usr1);
            users.add(usr2);
            users.add(usr3);

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("asta", asta);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        ArticoloAsta selected = (ArticoloAsta) session.getAttribute("selected");

        int price = Integer.parseInt(request.getParameter("price"));

        if (price <= selected.getPrice()) {
            response.getWriter().println(gson.toJson(new Result(false)));
            return;
        }

        synchronized (this) {
            if (selected.isConclusa() || selected.getExprire().getTime() < new Date().getTime()) {
                selected.setConclusa(true);
                response.getWriter().println(gson.toJson(new Result(false, selected.getWinner().getUsername(), selected.getPrice())));
                return;
            }

            selected.setPrice(price);
            selected.setWinner((User) session.getAttribute("user"));
            selected.setOffers(selected.getOffers() + 1);
        }

        response.getWriter().println(gson.toJson(new Result(true)));
    }
}
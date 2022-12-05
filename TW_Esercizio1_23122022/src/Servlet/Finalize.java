package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Group;
import Beans.GroupDb;
import Beans.Prodotto;
import Beans.User;
import Beans.UserDb;

public class Finalize extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if (this.getServletContext().getAttribute("users") == null
                || this.getServletContext().getAttribute("groups") == null) {
            GroupDb groups = new GroupDb();
            UserDb users = new UserDb();

            String prodotto = "";
            String file = "";

            try {
                BufferedReader br = new BufferedReader(new FileReader(new File("./prodotti.json")));

                while ((prodotto = br.readLine()) != null) {
                    file += prodotto.replace("[", "").replace("]", "");
                }
            } catch (IOException e) {
                throw new ServletException(e.getMessage());
            }

            StringTokenizer st = new StringTokenizer(file, ",\n");
            List<Prodotto> prodotti = new ArrayList<Prodotto>();

            for (int i = 0; i <= st.countTokens() / 2; i++) {
                prodotto = st.nextToken() + "," + st.nextToken();
                System.out.println(prodotto);
                prodotti.add(gson.fromJson(prodotto, Prodotto.class));
            }

            int i = 0;
            for (Prodotto prod : prodotti) {
                Group group1 = new Group(i++, prod);
                groups.add(group1);
            }

            this.getServletContext().setAttribute("users", users);
            this.getServletContext().setAttribute("groups", groups);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        Group group = (Group) session.getAttribute("currentGroup");

        Prodotto acquistato = group.getAcquisto();
        Prodotto prodotto = group.getProdotto();

        if (request.getParameter("add") != null) {
            if (prodotto.getQuantita() >= 0) {
                acquistato.setQuantita(acquistato.getQuantita() + 1);
                prodotto.setQuantita(prodotto.getQuantita() - 1);
            }
        }

        if (request.getParameter("concludi") != null) {
            if (session.getAttribute("concluded") == null) {
                session.setAttribute("concluded", true);
                group.setConcluded(group.getConcluded() + 1);
            }
        }

        if (group.getUsers().size() == group.getConcluded() || prodotto.getQuantita() == 0) {

            group.setProdotto(null);
            session.removeAttribute("currentUser");
            session.removeAttribute("currentGroup");
            session.setAttribute("concluded", true);
            this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }


        this.getServletContext().getRequestDispatcher("/cart.jsp").forward(request, response);
    }
}
package Servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.Tennista;

public class TennistiStats extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if (this.getServletContext().getAttribute("tennisti") == null) {

            Tennista[][] tennisti = new Tennista[4][32];
            Random random = new Random(System.currentTimeMillis());

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 32; j++) {
                    int rankingATP = random.nextInt(128);
                    int titols = random.nextInt(10);
                    int wins = random.nextInt(50);
                    int lose = random.nextInt(50);
                    String surname = randomSurname(random.nextInt(10));

                    tennisti[i][j] = new Tennista(rankingATP, titols, wins, lose, surname);
                }
            }

            this.getServletContext().setAttribute("tennisti", tennisti);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String surname = request.getParameter("surname");
        Tennista[][] tennisti = (Tennista[][])this.getServletContext().getAttribute("tennisti");

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 32; j++) {
                if ( tennisti[i][j].getSurname().equals(surname)) {
                    response.getWriter().println(gson.toJson(tennisti[i]));
                    return;
                }
            }
        }

        response.getWriter().println(gson.toJson(false));
        return;
    }

    private String randomSurname(int random) {
        switch (random) {
            case 1:
                return "Cavallini";
            case 2:
                return "Pieralberto";
            case 3:
                return "Casadio";
            case 4:
                return "Bellavista";
            case 5:
                return "Fuzzi";
            case 6:
                return "Somma";
            case 7:
                return "Panigalli";
            case 8:
                return "Federer";
            case 9:
                return "Verstappen";
            case 10:
                return "Hamilton";
            default:
                return "";
        }
    }
}
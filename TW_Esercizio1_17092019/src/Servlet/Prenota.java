package Servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.Albergo;
import Beans.Prenotazioni;

public class Prenota extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();

        if ( this.getServletContext().getAttribute("alberghi") == null ) {
            Map<Integer,Albergo> alberghi = new HashMap<Integer,Albergo>();
            List<Prenotazioni> prenotazioni = new ArrayList<Prenotazioni>();

            try {
                File file = new File("alberghi.txt");
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;

                while ((line = br.readLine()) != null) {
                    StringTokenizer st = new StringTokenizer(line,",");
                    int id = Integer.parseInt(st.nextToken());
                    int camereTotali = Integer.parseInt(st.nextToken());
                    float prezzoStatico = Float.parseFloat(st.nextToken()); 

                    alberghi.put(id,new Albergo(id, camereTotali, prezzoStatico));
                }

                this.getServletContext().setAttribute("alberghi", alberghi);
                this.getServletContext().setAttribute("prenotazioni", prenotazioni);
            } catch (IOException e) {
                throw new ServletException(e.getMessage());
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Map<Integer,Albergo> alberghi = (Map<Integer,Albergo>)this.getServletContext().getAttribute("alberghi");
        HttpSession session = request.getSession();

        session.setAttribute("numAlberghi", alberghi.size());
        this.getServletContext().getRequestDispatcher("/home.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int checkIn = Integer.parseInt(request.getParameter("checkin"));
        int checkOut = Integer.parseInt(request.getParameter("checkout"));

        HttpSession session = request.getSession(false);

        Map<Integer,Albergo> alberghi = (Map<Integer,Albergo>)this.getServletContext().getAttribute("alberghi");
        List<Prenotazioni> prenotazioni = (List<Prenotazioni>)this.getServletContext().getAttribute("prenotazioni");

        Prenotazioni miaPrenotazione = (Prenotazioni)session.getAttribute("prenotazione");

        Albergo albergo = alberghi.get(id);
        Float percentuale = 0f;

        for (Prenotazioni prenotazione : prenotazioni) {
            if ( prenotazione.getId() == id && !prenotazione.isFinalized()) {
                if (miaPrenotazione == null || !session.getId().equals(miaPrenotazione.getSession().getId()) ) {
                    if ( checkOut >= prenotazione.getCheckin() && checkOut <= prenotazione.getCheckout() ) {
                        percentuale += 10f;
                    } else if ( checkIn >= prenotazione.getCheckin() && checkIn <= prenotazione.getCheckout() ) {
                        percentuale += 10f;
                    } else if ( checkIn <= prenotazione.getCheckin() && checkOut >= prenotazione.getCheckout() ) {
                        percentuale += 10f;
                    }
                }
               
            }
        }

        if ( percentuale != 0f ) {
            percentuale = (percentuale / 100f) + 1f;
        } else {
            percentuale = 1f;
        }
        
        if ( miaPrenotazione == null ) {
            miaPrenotazione = new Prenotazioni(id, checkIn, checkOut, albergo.getPrezzoStatico() * percentuale, session);
            prenotazioni.add(miaPrenotazione);
        } else {
            miaPrenotazione.setPrice( albergo.getPrezzoStatico() * percentuale);
        }
        
        session.setAttribute("prenotazione", miaPrenotazione);

        this.getServletContext().setAttribute("prenotazioni", prenotazioni);

        response.getWriter().println(gson.toJson(miaPrenotazione.getPrice()));
    }
}

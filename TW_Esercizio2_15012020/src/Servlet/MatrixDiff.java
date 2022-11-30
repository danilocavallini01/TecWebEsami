package Servlet;

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

public class MatrixDiff extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
        if ( this.getServletContext().getAttribute("sessions") == null) {
            List<HttpSession> sessions = new ArrayList<HttpSession>();

            this.getServletContext().setAttribute("sessions", sessions);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer rows = Integer.parseInt(request.getParameter("rows"));
        Integer cols = Integer.parseInt(request.getParameter("cols"));
        String matrixStringA = request.getParameter("matrixA");
        String matrixStringB = request.getParameter("matrixB");

        StringTokenizer tokenizerMatrixA = new StringTokenizer(matrixStringA,",");
        StringTokenizer tokenizerMatrixB = new StringTokenizer(matrixStringB,",");

        Integer [][] matrixResult = new Integer[rows][cols];

        for ( int i = 0; i < rows; i++ ) {
            for ( int j = 0; j < cols; j++) {
                matrixResult[i][j] = compute(tokenizerMatrixA,tokenizerMatrixB);
            }
        }

        synchronized(this) {
            HttpSession session = request.getSession();
            List<HttpSession> sessions = (List<HttpSession>)this.getServletContext().getAttribute("sessions");

            int index = -1;
            for ( int i = 0; i < sessions.size(); i++ ) {
                if ( session.getId().equals(sessions.get(i).getId())) {
                    index = i;
                    break;
                }
            }

            if ( index == -1 ) {
                session.setAttribute("operazioni", 1);
                sessions.add(session);
            } else {
                int operazioni = (int)session.getAttribute("operazioni") + 1;
                session.setAttribute("operazioni", operazioni);
                sessions.set(index, session);
            }
            this.getServletContext().setAttribute("sessions", sessions);
        }
        
        response.getWriter().println(gson.toJson(matrixResult));
    }

    private Integer compute(StringTokenizer st1, StringTokenizer st2) {
        return (Integer.parseInt(st1.nextToken()) - Integer.parseInt(st2.nextToken()));
    }
}
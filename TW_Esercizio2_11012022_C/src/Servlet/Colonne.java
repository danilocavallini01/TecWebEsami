package Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.Result;

public class Colonne extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String matrixString = request.getParameter("matrixs");
        Integer[] matrix = gson.fromJson(matrixString, Integer[].class);
        int sumColonna = matrix[0] + matrix[4] + matrix[8] + matrix[12];
        int sum = 0;

        for ( int i = 0; i < 4; i++ ) {
            for ( int j = 0; j < 4; j++) {
                sum += matrix[j*4 + i];
            }

            if ( sum != sumColonna ) {
                response.getWriter().println(gson.toJson(new Result(false)));
                return;
            }
            sum = 0;
        }

        response.getWriter().println(gson.toJson(new Result(true, sumColonna)));
    }
}
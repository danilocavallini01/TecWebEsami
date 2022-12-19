package Servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.Result;

public class Righe extends HttpServlet {
	private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String matrixString = request.getParameter("matrix");
        Integer[][] matrix = gson.fromJson(matrixString, Integer[][].class);
        int sumColonna = matrix[0][0] + matrix[0][1] + matrix[0][2];
        int sum = 0;

        for ( int i = 0; i < 3; i++ ) {
            for ( int j = 0; j < 3; j++) {
                sum += matrix[j][i];
            }

            if ( sum != sumColonna ) {
                response.getWriter().println(gson.toJson(new Result(false, 0)));
                return;
            }
            sum = 0;
        }
        
        
        response.getWriter().println(gson.toJson(new Result(true,sumColonna)));
    }
}

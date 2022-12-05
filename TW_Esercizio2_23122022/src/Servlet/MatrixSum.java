package Servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MatrixSum extends HttpServlet {
    private Gson gson;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gson = new Gson();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String matrix = request.getParameter("matrixs");
        Integer[][] matrixs = gson.fromJson(matrix, Integer[][].class);

        Integer[] result = new Integer[matrixs[0].length];
        int rowResult = 0;
        for (int i = 0; i < result.length; i++ ) {
            for ( int j = 0; j < matrixs.length; j++ ) {
                rowResult += matrixs[j][i];
            }
            result[i] = rowResult;
            rowResult = 0;
        }

        response.getWriter().println(gson.toJson(result));
    }
}
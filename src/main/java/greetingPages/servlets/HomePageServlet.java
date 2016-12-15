package greetingPages.servlets;

import greetingPages.Jetty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        BufferedReader reader = new BufferedReader(new InputStreamReader(Jetty.class.getResourceAsStream("index.html")));
        String line;
        String page = "";
        while ((line = reader.readLine()) != null) {
            page += line;
        }

        writer.write(page);
        writer.flush();
    }
}
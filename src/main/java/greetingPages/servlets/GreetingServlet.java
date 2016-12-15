package greetingPages.servlets;

import greetingPages.Jetty;
import greetingPages.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

public class GreetingServlet extends HttpServlet {

    private Template template = new Template() {{
        add("page1", "Welcome to Page 1");
        add("page2", "Welcome to Page 2");
        add("page3", "Welcome to Page 3");
    }};

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();

        String param = req.getParameter("pages");
        int value = incrementAttributeValue(param, session);

        String message = generateMessage(param, value);
        renderPage(Jetty.class.getResourceAsStream("message.html"), message, writer);
    }

    private int incrementAttributeValue(String param, HttpSession session) {
        if (session.getAttribute(param) == null) {
            session.setAttribute(param, 0);
        }
        Integer count = (Integer) session.getAttribute(param) + 1;
        session.setAttribute(param, count);
        return (int) session.getAttribute(param);
    }

    private String generateMessage(String param, int value) throws ServletException, IOException {
        if (value < 2) {
            return template.getValue(param);
        }
        return "You already were here";
    }

    private void renderPage(InputStream stream, String message, PrintWriter writer) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line;
        String page = "";
        while ((line = reader.readLine()) != null) {
            page += line;
        }
        page = page.replace("message", message);
        writer.write(page);
        writer.flush();
    }
}
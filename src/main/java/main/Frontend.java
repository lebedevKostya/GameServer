package main;

import html.PageGenerator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Murat on 27.06.2017.
 */
public class Frontend extends HttpServlet implements Runnable {

    private AtomicInteger value = new AtomicInteger();
    private static int handleCount = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        Map<String, Object> pageVariables = new HashMap<>();

        HttpSession session = request.getSession();
        Integer sessionId = (Integer) session.getAttribute("sessionId");

        if (sessionId == null) {
            sessionId = value.getAndIncrement();
            session.setAttribute("sessionId", sessionId);
            handleCount++;
        }
        pageVariables.put("sessionId", sessionId);
        pageVariables.put("message", "YourSessionId");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer sessionId = (Integer) session.getAttribute("sessionId");
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", "HelloUserYourSessionId");
        pageVariables.put("sessionId", sessionId);
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        handleCount++;


    }


    @Override
    public void run() {
        while (true){
            System.out.println(handleCount);
            TimeHelper.sleep();
        }
    }
}





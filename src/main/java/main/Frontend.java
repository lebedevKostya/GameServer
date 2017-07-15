package main;

import html.PageGenerator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.imageio.metadata.IIOMetadataNode;
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

    private AtomicInteger value = new AtomicInteger(1);
    private static int handleCount = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // создание сессии
        HttpSession session = request.getSession();
        String sessionId = session.getId();

        Map<String, Object> pageVariables = new HashMap<>();


        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            userId = value.getAndIncrement();
            session.setAttribute("userId", userId);
            handleCount++;
        }
        pageVariables.put("sessionId", sessionId);
        pageVariables.put("userId", userId);
        pageVariables.put("message", "YourS Id`s");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getParameter("sessionId");
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", "HelloUserYourSessionId");
        pageVariables.put("sessionId", sessionId);
        pageVariables.put("userId", userId);

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





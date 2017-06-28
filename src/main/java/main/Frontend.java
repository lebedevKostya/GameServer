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
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Murat on 27.06.2017.
 */
public class Frontend extends HttpServlet {
    private AtomicInteger value = new AtomicInteger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "YourSessionId");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);


    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "HelloUserYourSessionId");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    private Map<String,Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();

        if (request.getParameter("sessionId")==null) {
            pageVariables.put("sessionId", getId());
            return pageVariables;
        } else {
            pageVariables.put("sessionId",request.getParameter("sessionId"));
            return pageVariables;
        }


    }



    public Integer getId() {
        return value.getAndIncrement();
    }
}


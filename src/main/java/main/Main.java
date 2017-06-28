package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;


/**
 * Hello world!
 *
 */
public class Main
{
    public static void main( String[] args ) throws Exception {
        simpleServer();
    }

    private static void simpleServer() throws Exception,InterruptedException {
        Frontend frontend = new Frontend();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(frontend), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }


}

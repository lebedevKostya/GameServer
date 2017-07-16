package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Hello world!
 *
 */
public class Main
{
    private static final int N = 2;

    public static void main(String[] args ) throws Exception {
        simpleServer();

    }

    private static void simpleServer() throws Exception,InterruptedException {

        AccountService accountService = new AccountService();
        Frontend frontend = new Frontend();
        accountService.addNewUser(new UserProfile("admin"),1);
        accountService.addNewUser(new UserProfile("test"),2);

       /* ExecutorService pool = Executors.newFixedThreadPool(N);
        pool.submit(new Frontend());*/

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(frontend), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        server.join();
    }


}

package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;


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

        Server server = new Server(8080);
        server.setHandler(frontend);

        server.start();
        server.join();
    }


}

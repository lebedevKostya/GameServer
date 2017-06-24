package main;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Murat on 23.06.2017.
 */
public class Frontend extends AbstractHandler implements Runnable {
    private static String GAME_NAME = "/test/"; // что за имя игры?
    private Address address;
    private MessageSystem ms;

    private Map<String, Integer> nameToId = new HashMap<>();

    public Address getAddress() {
        return address;
    }

    public void handle (String target,Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response)
            throws IOException, ServletException {

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);

        // что это?
       //if (!target.equals(GAME_NAME))
       //     return;

        String name = "Tully";
        Integer id = nameToId.get(name);

        if (id != null) {
            response.getWriter().println("<h1>User name: " + name + " Id: " + id +" </h1>"); // Ответ клиенту с id
        } else {
            response.getWriter().println("<h1>Wait for authorization</h1>"); // Ответ клиенту без id (первая сессия) + действия
            Address addressAS = ms.getAddressService().getAddress(AccountService.class); //nullpointer
            ms.sendMessage(new MsgGetUserId(getAddress(), addressAS, name));
        }

    }


    @Override
    public void run() {
        while (true) {
            TimeHelper.sleep(10);
        }
    }

    public void setId (String name, Integer id) {
        nameToId.put(name, id);
    }


}

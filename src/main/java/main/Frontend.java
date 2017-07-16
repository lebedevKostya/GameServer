package main;

import html.PageGenerator;

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
 * Class who handles user requests
 *
 * Created by Murat on 27.06.2017.
 */
public class Frontend extends HttpServlet implements Runnable {

    private AtomicInteger value = new AtomicInteger(3);
    private static int handleCount = 1;
    private Map<String, UserProfile> sessionIdToUserProfile = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // создание сессии и объкта пользавтеля при первом запросе и добавление их в карту sessionIdToUserSession
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        UserProfile userProfile = new UserProfile();
        sessionIdToUserProfile.put(sessionId, userProfile);

        // после чего возвращает пользователю страницу с sessionId, а так же запрос "Введите свое имя"
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("sessionId", sessionId);
        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));




        /*Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            userId = value.getAndIncrement();
            session.setAttribute("userId", userId);
            handleCount++;
        }
        pageVariables.put("sessionId", sessionId);
        pageVariables.put("userId", userId);
        pageVariables.put("message", "YourS Id`s");

        response.getWriter().println(PageGenerator.instance().getPage("page.html", pageVariables));*/


        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Вместе с именем приложение получает от пользователя ранее переданный ему sessionId,
        String sessionId = request.getParameter("sessionId");
        String userName = request.getParameter("userName");
        // находит по этому Id объект пользовательской сессии и записывает в него присланное имя.
        sessionIdToUserProfile.get(sessionId).setUserName(userName);
        // После изменения объекта userSession приложение отправляет пользователю страницу с
        // sessionId и статусом "Ждите авторизации".
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", "Wait For Authorization");
        pageVariables.put("sessionId", sessionId);
        response.getWriter().println(PageGenerator.instance().getPage("wait.html", pageVariables));

        // После отправки страницы приложение запрашивает у службы AccountService аутентификацию пользователя.
        // Служба AccountService работает в отдельном потоке и может найти userId по имени пользователя
        // (поиск может быть долгим, имитацию долгого поиска можно сделать через Thread.sleep(5000)).
        AccountService accountService = new AccountService(); // создает новый объект с другой картой!!!!!!!!!!!!!!!!!!!!!!!!!!
        Integer userId = accountService.getUserIdByUserName(userName);

        // добавить userId в объект userSession соответствующего пользователя.
        sessionIdToUserProfile.get(sessionId).setUserId(userId);

        // При следующем обращении пользователь получит страницу
        // "Здравствуйте: " + userName + " ваш userId: " + userId;
        if (userId != null) {
            return;
        }

        // Если пользователь запрашивает страницу раньше, чем AccountService нашел userId по имени,
        // он получает страницу с sessionId и статусом "Ждите авторизации".
        if (userId == null) {
            return;
        }

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





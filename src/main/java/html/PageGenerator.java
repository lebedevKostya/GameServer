package html;

/**
 * Created by Murat on 23.06.2017.
 */
public class PageGenerator {

    private static int refreshTime = 1000;

    private static String pagePart0 = "<html>" +
            "<head>" +
            "<script type='text/JavaScript'>" +
            "function setClientTime(){" +
            "currentTime = new Date();" +
            "hours = currentTime.getHours();" +
            "minutes = currentTime.getMinutes();" +
            "seconds = currentTime.getSeconds();" +
            "if (minutes < 10)" +
            "minutes = '0' + minutes;" +
            "if (seconds < 10)" +
            "seconds = '0' + seconds;" +
            "timeString = hours + ':' + minutes + ':' + seconds;" +
            "document.getElementById('ClientTime').innerHTML = timeString;" +
            "}" +
            "function refresh(){" +
            "location.reload(true);" +
            "}" +
            "</script>" +
            "</head>" +
            "<body onload='setInterval(function(){refresh()},"+ refreshTime +"); setClientTime();'>" +
            "<p>Client time: <span id='ClientTime'></span></p>" +
            "<p>Server time: ";

    private static String pagePart1 = "</p>" +
            "</body>" +
            "</html>";

    public static String getPage(){
        return pagePart0 + pagePart1;
    }
}

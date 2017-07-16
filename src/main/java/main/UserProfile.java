package main;

/**
 *
 *
 * Created by Murat on 16.07.2017.
 */
public class UserProfile {
    public String userName;
    public int userId;
    public String sessionId;

    public UserProfile(String userName, int userId, String sessionId) {
        this.userName = userName;
        this.userId = userId;
        this.sessionId = sessionId;
    }

    public UserProfile(String userName, int userId) {
        this.userName = userName;
        this.userId = userId;
        this.sessionId = "UnknownSessionId";
    }

    public UserProfile() {
        this.userName = "empty";
        this.userId = 0;
        this.sessionId = "empty";
    }

    public UserProfile(String userName) {
        this.userName = userName;
        this.userId = 0;
        this.sessionId = "empty";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getSessionId() {
        return sessionId;
    }
}

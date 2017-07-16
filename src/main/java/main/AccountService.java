package main;

import java.util.HashMap;
import java.util.Map;

/**
 * class who handle the authorization request
 *
 * Created by Murat on 16.07.2017.
 */
public class AccountService {
    private final Map<String, Integer> userNameToUserId;

    public AccountService() {
        userNameToUserId = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile, Integer userId) {
        userNameToUserId.put(userProfile.getUserName(), userId);
    }

    public Integer getUserIdByUserName(String userName) {
        return userNameToUserId.get(userName);
    }
}

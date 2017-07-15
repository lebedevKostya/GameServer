package main;

import java.util.HashMap;
import java.util.Map;

/**
 * class who handle the authorization request
 *
 * Created by Murat on 16.07.2017.
 */
public class AccountService {
    private final Map<Integer, UserProfile> userNameToUserId;

    public AccountService() {
        userNameToUserId = new HashMap<>();
    }

    public void addNewUser(UserProfile userProfile) {
        userNameToUserId.put(userProfile.getUserId(), userProfile);
    }

    public UserProfile getUserProfileByUserId(Integer userId) {
        return userNameToUserId.get(userId);
    }
}

package skkud.hw1.user;


import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();

    public User findByUserName(String userName) {
        return userMap.get(userName);
    }

    public boolean createAccount(String userName, String passWord) {
        if (userMap.containsKey(userName)) return false;
        User user = new User(userName, passWord);
        userMap.put(userName, user);
        return true;
    }

    public boolean login(String userName, String passWord) {
        if(!userMap.containsKey(userName)) return false;
        return userMap.get(userName).passWord.equals(passWord);
    }

    public void clear() {
        userMap.clear();
    }
}

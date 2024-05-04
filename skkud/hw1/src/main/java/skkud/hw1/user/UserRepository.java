package skkud.hw1.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import skkud.hw1.post.Post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();
    @Getter
    private String currentUser;

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
        return userMap.get(userName).hasEqualPassWord(passWord);
    }

    public void addUserPost(String userName, Post post) {
        User user = findByUserName(userName);
        user.addPost(post);
    }

    public void removeUserPost(String userName, Post post) {
        User user = findByUserName(userName);
        user.removePost(post);
    }

    public void clear() {
        userMap.clear();
    }

    public void changeCurrentUser(String newUser) {
        this.currentUser = newUser;
    }
}

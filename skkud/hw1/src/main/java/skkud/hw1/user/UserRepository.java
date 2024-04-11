package skkud.hw1.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import skkud.hw1.post.Post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();
    @Setter
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
        return userMap.get(userName).passWord.equals(passWord);
    }

    public void addUserPost(String userName, Post post) {
        User user = findByUserName(userName);
        user.postList.add(post.getId());
    }

    public void removeUserPost(String userName, Long postId) {
        User user = findByUserName(userName);
        user.postList.remove(postId);
    }

    public void clear() {
        userMap.clear();
    }
}

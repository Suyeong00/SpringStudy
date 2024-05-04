package skkud.hw1.user;


import skkud.hw1.post.Post;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private String userName;
    private String passWord;
    Set<Post> posts = new HashSet<>();
    // 변수명 postList -> posts
    // Set<Long> - > Set<Post>
    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    // passWord 멤버의 getter 를 삭제하고 hasEqualPassWord 라는 메소드를 추가하였는데
    // 이 메서드가 User 에 위치해야 하는지 아니면 UserRepository 라는 User 를 관리하는 메서드에 위치해야 하는지 궁금하다
    // 만약 UserRepository 에 위치해야 한다면 getter 없이 구현 가능?
    public Boolean hasEqualPassWord(String passWord) {
        return passWord.equals(this.passWord);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    /*
     *   Set<Post> postSet = user.getPosts();
     *   List<Post> posts = new ArrayList<>(postSet);
     *   대체하는 메소드
     *   이게 맞나? 하는 생각이 있음
     * */

    public List<Post> deliverUserPosts() {
        return new ArrayList<>(posts);
    }

}

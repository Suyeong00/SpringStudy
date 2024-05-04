package skkud.hw1.post;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class Post {
    @Getter
    @Setter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String content;
    @Getter
    private String userName;
    // 타임리프에서 모델 프로퍼티 접근법을 위해 @Getter

    public Post(String title, String content, String userName) {
        this.title = title;
        this.content = content;
        this.userName = userName;
    }

    public void changePost(Post post) {
        this.title = post.title;
        this.content = post.content;
    }


}

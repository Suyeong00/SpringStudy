package skkud.hw1.post;

import lombok.Data;

@Data
public class Post {
    private Long id;
    private String title;
    private String content;
    private String userName;

    public Post(String title, String content, String userName) {
        this.title = title;
        this.content = content;
        this.userName = userName;
    }
}

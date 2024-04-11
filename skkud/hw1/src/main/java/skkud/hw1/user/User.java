package skkud.hw1.user;


import lombok.Data;

@Data
public class User {
    String userName;
    String passWord;
    Long posts;

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}

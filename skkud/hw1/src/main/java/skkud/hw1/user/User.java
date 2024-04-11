package skkud.hw1.user;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class User {
    String userName;
    String passWord;

    @Getter
    @Setter
    Set<Long> postList = new HashSet<>();

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}

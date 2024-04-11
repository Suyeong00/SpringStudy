package skkud.hw1.controller;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import skkud.hw1.user.UserRepository;

@Controller
@RequestMapping("/basic/login")
@RequiredArgsConstructor // final이 붙은 대상으로 생성자를 만들어줌(생성자 주입)
public class LoginController {
    private final UserRepository userRepository;
    //private int loginFails = 0;

    @GetMapping
    public String loginMain() {
        //loginFails = 0;
        return "/basic/login";
    }

    @PostMapping
    public String login(Model model,
                        @RequestParam String userName,
                        @RequestParam String passWord) {
        if(userRepository.login(userName, passWord)) {
            userRepository.setCurrentUser(userName);
            return "redirect:/basic/posts";
        }
        //loginFails++;
        model.addAttribute("status", "로그인 실패");
        //model.addAttribute("loginFailes", loginFails);
        System.out.println("userName = " + userName);
        System.out.println("passWord = " + passWord);
        System.out.println("LoginController.login");
        return "/basic/login";
    }


    @PostConstruct
    public void init() {
        userRepository.createAccount("admin", "1234");
    }
}

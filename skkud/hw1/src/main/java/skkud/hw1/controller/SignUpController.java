package skkud.hw1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import skkud.hw1.user.UserRepository;

@Controller
@RequestMapping("/basic/signUp")
@RequiredArgsConstructor // final이 붙은 대상으로 생성자를 만들어줌(생성자 주입)
public class SignUpController {
    private final UserRepository userRepository;

    @GetMapping
    public String signUpMain() {
        return "/basic/signUp";
    }

    @PostMapping
    public String createAccount(@RequestParam String userName,
                                @RequestParam String passWord,
                                Model model) {
        if(userRepository.createAccount(userName, passWord))
            model.addAttribute("status", "회원가입 성공");
        else
            model.addAttribute("status", "회원가입 실패");
        return "/basic/signUp";
    }
}

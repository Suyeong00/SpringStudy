package skkud.hw1.controller;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skkud.hw1.post.Post;
import skkud.hw1.post.PostRepository;

import java.util.List;

@Controller
@RequestMapping("/basic/posts")
@RequiredArgsConstructor // final이 붙은 대상으로 생성자를 만들어줌(생성자 주입)
public class PostController {
    private final PostRepository postRepository;

    @GetMapping
    public String posts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "/basic/posts"; // 뷰 리턴
    }

    @PostConstruct
    public void init() {
        postRepository.save(new Post("post1", "111", "user1"));
        postRepository.save(new Post("post2", "222", "user2"));
    }

    @GetMapping("/{postId}")
    public String item(@PathVariable long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "basic/post";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postRepository.findById(postId);
        model.addAttribute("post", post);
        return "basic/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable Long postId,
                       @RequestParam String title,
                       @RequestParam String content,
                       Model model) {
        Post post = new Post(title, content, "tempUserName");
        postRepository.update(postId, post);
        //return "/basic/posts/{postId}"; << 왜 이렇게 하면 안되지?
        return "redirect:/basic/posts/{postId}";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(@RequestParam String title,
                       @RequestParam String content, Model model) {
        Post post = new Post(title, content, "tempUserName");
        postRepository.save(post);
        model.addAttribute("post", post);
        // ModelAttribute쓰면 더 쉽게 가능
        // Q) ModelAttribute는 객체를 생성하고 요청 파라미터의 값을 프로퍼티 접근법으로 입력해준다고 했다
        // 그러면 해당 객체의 필드를 프로퍼티 접근이 가능하도록 set메소드를 열어줘야 하는건가?
        //return "basic/post";
        return "redirect:/basic/posts/" + post.getId(); // PRG 패턴
    }
}


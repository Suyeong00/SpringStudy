package skkud.hw1.controller;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import skkud.hw1.post.Post;
import skkud.hw1.post.PostRepository;
import skkud.hw1.user.User;
import skkud.hw1.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/basic/posts")
@RequiredArgsConstructor // final이 붙은 대상으로 생성자를 만들어줌(생성자 주입)
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String posts(Model model) {
        List<Post> posts = postRepository.findAll();
        String userName = userRepository.getCurrentUser();
        model.addAttribute("posts", posts);
        model.addAttribute("userName", userName);
        return "/basic/posts"; // 뷰 리턴
    }

    @PostConstruct
    public void init() {
        userRepository.setCurrentUser("admin");
        String userName = "admin";
        Post post1 = new Post("post1", "111", userName);
        Post post2 = new Post("post2", "222", userName);
        postRepository.save(post1);
        userRepository.addUserPost(userName, post1);
        postRepository.save(post2);
        userRepository.addUserPost(userName, post2);
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
        String userName = userRepository.getCurrentUser();
        Post post = new Post(title, content, userName);
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
        String userName = userRepository.getCurrentUser(); // 현재 유저의 로그인 정보

        Post post = new Post(title, content, userName);
        
        postRepository.save(post); // postRepository에 게시글 저장
        userRepository.addUserPost(userName, post); // userRepository에 게시글 저장
        
        model.addAttribute("post", post);
        // ModelAttribute쓰면 더 쉽게 가능
        // Q) ModelAttribute는 객체를 생성하고 요청 파라미터의 값을 프로퍼티 접근법으로 입력해준다고 했다
        // 그러면 해당 객체의 필드를 프로퍼티 접근이 가능하도록 set메소드를 열어줘야 하는건가?
        //return "basic/post";
        return "redirect:/basic/posts/" + post.getId(); // PRG 패턴
    }

    @GetMapping("/myposts")
    public String myPosts(Model model) {
        String userName = userRepository.getCurrentUser();
        User user = userRepository.findByUserName(userName);
        Set<Long> postList = user.getPostList();
        List<Post> posts = new ArrayList<>();
        for (Long l : postList) {
            Post post = postRepository.findById(l);
            posts.add(post);
        }
        model.addAttribute("posts", posts);
        return "basic/myposts";
    }

    @GetMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        System.out.println("PostController.deletePost");
        String userName = userRepository.getCurrentUser();
        userRepository.removeUserPost(userName, postId);
        postRepository.remove(postId);

        return "redirect:/basic/posts";
    }
}


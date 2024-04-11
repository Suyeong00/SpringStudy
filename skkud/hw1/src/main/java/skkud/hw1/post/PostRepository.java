package skkud.hw1.post;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // @Repository도 컴포넌트 스캔의 대상이 된다
public class PostRepository {
    private static final Map<Long, Post> postMap = new HashMap<>();
    private static long sequence = 0L;
    // [질문]
    // 싱글톤으로 관리되는 객체의 필드에 static을 붙이는 이유?
    // static은 여러 객체가 동일한 필드를 공유하기 위해 / 객체 생성 전에 사용하기 위해서
    // 싱글톤이라면 어차피 객체는 하나, 그렇다면 static을 사용하는 이유는 객체 생성 전에 사용하기 위해서?

    public Post save(Post post) {
        post.setId(++sequence);
        postMap.put(post.getId(), post);
        return post;
    }

    public Post findById(Long id) {
        return postMap.get(id);
    }

    public List<Post> findAll() {
        return new ArrayList<>(postMap.values());
    }

    public void update(Long postId, Post updatePost) {
        Post findPost = findById(postId);
        findPost.setTitle(updatePost.getTitle());
        findPost.setContent(updatePost.getContent());
    }

    public void clearMap() { // Test를 위함
        postMap.clear();
    }
}

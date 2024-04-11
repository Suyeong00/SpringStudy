package skkud.hw1.post;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PostRepositoryTest {
    PostRepository postRepository = new PostRepository();

    @AfterEach
    void afterEach() {
        postRepository.clearMap();
    }

    @Test
    void save() {
        // given
        Post post = new Post("첫 게시물", "Hello", "com/example/user");
        // when
        postRepository.save(post);

        // then
        Post post1 = postRepository.findById(post.getId());
        assertThat(post).isEqualTo(post1);
    }

    @Test
    void findall() {
        // given
        Post post1 = new Post("asdf", "asdf11", "user1");
        Post post2 = new Post("asdfk", "asdfk11", "user2");

        postRepository.save(post1);
        postRepository.save(post2);
    }
}

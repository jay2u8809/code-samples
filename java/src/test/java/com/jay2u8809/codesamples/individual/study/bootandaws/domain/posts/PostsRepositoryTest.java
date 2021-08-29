package com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    public void getPostsTest() {

        // given
        String title = "TEST POSTS";
        String content = "TEST CONTENTS";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author("test@testtesttest.com")
                .build();

        postsRepository.save(posts);

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }
}
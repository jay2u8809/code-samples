package com.example.junitsamples.study.domain.posts;

import com.example.junitsamples.study.common.CommonExtends;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @SpringBootTest
 *   별다른 설정이 없다면 H2 데이터베이스를 자동으로 실행한다.
 * 실제 실행 Query 보기
 *   application.properties: spring.jpa.show_sql=true 설정
 *   로그: Hibernate: insert into posts (id, author, content, title) values (null, ?, ?, ?)
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest extends CommonExtends {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        this.postsRepository.deleteAll();
    }

    @Test
    public void get_posts_contents() {
        // given
        String title = "Test Title";
        String content = "Test Contents";

        this.postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@test.com")
                .build()
        );

        // when
        List<Posts> postsList = this.postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void register_BaseTimeEntity_test() {
        // given
        String title = "Test Title";
        String content = "Test Contents";
        LocalDateTime now = LocalDateTime.of(2022, 3, 22, 0, 0, 0);
        log.info("now date= {}", now);
        this.postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@test.com")
                .build());

        // when
        List<Posts> postsList = this.postsRepository.findAll();

        // then
        Posts post = postsList.get(0);
        log.info(">>>> createdDt= {}, updatedDt= {}", post.getCreatedDt(), post.getUpdatedDt());
        assertThat(post.getCreatedDt()).isAfter(now);
        assertThat(post.getUpdatedDt()).isAfter(now);
    }

    @Test
    public void findAllDesc_test() {
        // given
        String title = "Test Title";
        String content = "Test Contents";
        LocalDateTime now = LocalDateTime.of(2022, 3, 22, 0, 0, 0);
        log.info("now date= {}", now);
        for (int i = 0; i < 10; i++) {
            this.postsRepository.save(Posts.builder()
                    .title(title + i)
                    .content(content + i)
                    .author("test" + i + "@test.com")
                    .build());
        }

        // when
        List<Posts> postsList = this.postsRepository.findAllDesc();

        // then
        assertThat(postsList.size()).isNotZero();
        postsList.forEach(f -> assertThat(f.getUpdatedDt()).isAfter(now));
    }
}
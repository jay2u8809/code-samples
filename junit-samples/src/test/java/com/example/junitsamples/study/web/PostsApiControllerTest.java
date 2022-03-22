package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.common.CommonExtends;
import com.example.junitsamples.study.domain.posts.Posts;
import com.example.junitsamples.study.domain.posts.PostsRepository;
import com.example.junitsamples.study.web.dto.PostsSaveRequestDto;
import com.example.junitsamples.study.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @SpringBootTest
 *   JPA 기능까지 한번에 테스트 할 경우 @SpringBootTest 와 TestRestTemplate 클래스를 사용한다
 *   WebMvcTest 어노테이션의 경우, JPA 기능이 동작하지 않기에 Controller, ControllerAdvice 등 외부 연동과 관련된 부분만 활성화된다.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest extends CommonExtends {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_save_test() throws Exception {
        // given
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = ApiEndPoint.ApiEnvRoot.LOCAL + this.port + ApiEndPoint.PostApiController.SAVE;
        log.debug("request url: {}", url);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = this.postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void posts_update_test() throws Exception {
        // given
        String title = "title";
        String content = "content";
        Posts savedPosts = this.postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("author")
                .build());

        Long updatedId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = ApiEndPoint.ApiEnvRoot.LOCAL + this.port + ApiEndPoint.PostApiController.BASIC + "/" + updatedId;
        log.info("request url: {}", url);

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = this.postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(postsList.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    public void posts_findById_test() throws Exception {
    }
}
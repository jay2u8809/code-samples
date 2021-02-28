package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.Posts;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.PostsRepository;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsSaveRequestsDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)     // @WebMvcTest의 경우 JPA 기능이 작동하지 않음.
public class PostsApiControllerTest {

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
    public void register_posts() throws Exception {

        // given
        String title = "TITLE";
        String content = "CONTENT";

        PostsSaveRequestsDto requestsDto = PostsSaveRequestsDto.builder()
                                                                .title(title)
                                                                .content(content)
                                                                .author("LEE")
                                                                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/";

        // when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestsDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    public void update_posts() throws Exception {

        // given
        Posts savedPosts = postsRepository.save(Posts.builder().title("updated title").content("updated content").author("updated author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "updated title2";
        String expectedContent = "updated content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder().title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestDtoHttpEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestDtoHttpEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    public void register_baseTimeEntity() {

        // given
        LocalDateTime now = LocalDateTime.of(2020, 2, 23, 0, 0, 0);
        postsRepository.save(Posts.builder().title("BaseTime Title").content("BaseTime Content").author("BaseTime Author").build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> Create Date=" + posts.getCreatedDate() + ", modified Date = " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}
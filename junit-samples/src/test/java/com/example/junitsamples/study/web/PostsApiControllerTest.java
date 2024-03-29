package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.common.CommonExtends;
import com.example.junitsamples.study.domain.posts.Posts;
import com.example.junitsamples.study.domain.posts.PostsRepository;
import com.example.junitsamples.study.web.dto.PostsResponseDto;
import com.example.junitsamples.study.web.dto.PostsSaveRequestDto;
import com.example.junitsamples.study.web.dto.PostsUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

/**
 * @SpringBootTest
 *   JPA 기능까지 한번에 테스트 할 경우 @SpringBootTest 와 TestRestTemplate 클래스를 사용한다
 *   WebMvcTest 어노테이션의 경우, JPA 기능이 동작하지 않기에 Controller, ControllerAdvice 등 외부 연동과 관련된 부분만 활성화된다.
 * TestRestTemplate 사용법
 *   https://www.javaguides.net/2019/06/spring-resttemplate-get-post-put-and-delete-example.html
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

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private Posts beforeSaved;

    @Before
    public void setUp() throws Exception {
        String title = "title";
        String content = "content";
        beforeSaved = this.postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("author")
                .build());
        log.info("Before Saved Id: {}", this.beforeSaved.getId());

        this.mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        this.postsRepository.deleteAll();
    }

    /**
     * @WithMockUser(roles = "USER")
     *   인증된 가짜 사용자를 만들어 사용
     *   roles 에  권한을 추가한다
     *   ROLE_USER 권한을 가진 사용자가 API 를 요청하는 것과 동일한 효과를 가지게 된다.
     *   MockMve 에서만 작동한다.
     * @throws Exception
     */
    @Test
    @WithMockUser(roles = "USER")
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
//        ResponseEntity<Long> responseEntity
//                = restTemplate.postForEntity(url, requestDto, Long.class);
        /**
         * ObjectMapper(): body 영역을 문자열로 표현하기 위해 사용, JSON 으로 변환한다.
         */
        this.mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = this.postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void posts_update_test() throws Exception {
        // given
        assertThat(this.beforeSaved).isNotNull();
        log.info("Before Saved Id: {}", this.beforeSaved.getId());

        Long updatedId = this.beforeSaved.getId();
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
//        ResponseEntity<Long> responseEntity
//                = this.restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        this.mvc.perform(MockMvcRequestBuilders.put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = this.postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(postsList.get(0).getContent()).isEqualTo(expectedContent);

    }

    @Test
    @WithMockUser(roles = "USER")
    public void posts_findById_test() throws Exception {
        // given
        assertThat(this.beforeSaved).isNotNull();
        log.info("Before Saved Id: {}", this.beforeSaved.getId());

//        String url = ApiEndPoint.ApiEnvRoot.LOCAL + this.port + ApiEndPoint.PostApiController.FIND_BY_ID;
//        log.info("request url: {}", url);
        String url = ApiEndPoint.ApiEnvRoot.LOCAL + this.port + ApiEndPoint.PostApiController.BASIC + "/" + this.beforeSaved.getId();
        log.info("request url: {}", url);

        // when
        /**
         * error
         *   Type definition error: [simple type, class com.example.junitsamples.study.web.dto.PostsResponseDto]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.junitsamples.study.web.dto.PostsResponseDto` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
         *     at [Source: (PushbackInputStream); line: 1, column: 2]
         *   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.example.junitsamples.study.web.dto.PostsResponseDto]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.junitsamples.study.web.dto.PostsResponseDto` (no Creators, like default construct, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
         *     at [Source: (PushbackInputStream); line: 1, column: 2]
         *  resolved: PostsResponseDto에 @NoArgsConstructor 추가, 기본 생성자가 없어서 발생하는 에러
         */
//        ResponseEntity<PostsResponseDto> responseEntity
//                = this.restTemplate.getForEntity(url, PostsResponseDto.class, this.beforeSaved.getId());
//        log.info("response entity id: {}", responseEntity.getBody().getId());
        this.mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody().getId()).isGreaterThan(0L);
    }

    @Test
    public void delete_test() throws Exception {
        // given
        assertThat(this.beforeSaved).isNotNull();
        log.info("Before Saved Id: {}", this.beforeSaved.getId());
        Map<String, Long> param = new HashMap<>();
        param.put("id", this.beforeSaved.getId());

        String url = ApiEndPoint.ApiEnvRoot.LOCAL + this.port + ApiEndPoint.PostApiController.DELETE;
        log.info("request url: {}", url);

        // when
        this.restTemplate.delete(url, param);

        // then
    }
}
package com.example.junitsamples.study.service.posts;

import com.example.junitsamples.study.common.CommonExtends;
import com.example.junitsamples.study.domain.posts.Posts;
import com.example.junitsamples.study.domain.posts.PostsRepository;
import com.example.junitsamples.study.web.dto.PostsListResponseDto;
import com.example.junitsamples.study.web.dto.PostsResponseDto;
import com.example.junitsamples.study.web.dto.PostsSaveRequestDto;
import com.example.junitsamples.study.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest extends CommonExtends {

    @Autowired
    private PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    private Posts beforeSaved;

    @Before
    public void setUp() {
        String title = "New Posts";
        String content = "New Content";
        String author = "New Author";
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto(title, content, author);
        this.beforeSaved = this.postsRepository.save(requestDto.toEntity());
        log.info("Before Saved Id: {}", this.beforeSaved.getId());
    }

    @After
    public void tearDown() {
        log.info("After Delete All");
        this.postsRepository.deleteAll();
    }

    @Test
    public void save_test() {
        // given
        String title = "New Posts";
        String content = "New Content";
        String author = "New Author";
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto(title, content, author);

        // when
        Long savedId = this.postsService.save(requestDto);

        // then
        assertThat(savedId).isGreaterThan(0L);
    }

    @Test
    public void update_test() {
        // given
        assertThat(this.beforeSaved).isNotNull();
        Long savedId = this.beforeSaved.getId();
        log.info("Before Saved Id: {}", savedId);

        String title = "Updated Title";
        String content = "Updated Content";
        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(title, content);

        // when
        Long updatedId = this.postsService.update(savedId, requestDto);
        log.info("updated id: {}", updatedId);

        // then
        assertThat(updatedId).isEqualTo(savedId);
    }

    /**
     * Exception Test
     * https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
     */
    @Test(expected = IllegalArgumentException.class)
    public void update_test_id_not_exist() {
        // given
        Long wrongId = -1L;
        String title = "Updated Title";
        String content = "Updated Content";
        PostsUpdateRequestDto requestDto = new PostsUpdateRequestDto(title, content);

        // when
        Long updatedId = this.postsService.update(wrongId, requestDto); // exception
        log.info("updated id: {}", updatedId);

        // then: Nothing
    }

    @Test
    public void findById_test() {
        // given
        assertThat(this.beforeSaved).isNotNull();
        Long savedId = this.beforeSaved.getId();
        log.info("Before Saved Id: {}", savedId);

        // when
        PostsResponseDto dto = this.postsService.findById(savedId);

        // then
        assertThat(dto.getId()).isEqualTo(savedId);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findById_test_id_not_exist() {
        // given
        Long wrongId = -1L;

        // when
        PostsResponseDto dto = this.postsService.findById(wrongId); // exception

        // then: Nothing
    }

    @Test
    public void findAllDesc_test() {
        // given
        String title = "New Posts";
        String content = "New Content";
        String author = "New Author";
        PostsSaveRequestDto requestDto = new PostsSaveRequestDto(title, content, author);
        Posts afterSaved = this.postsRepository.save(requestDto.toEntity());
        log.info("Before Saved Id: {}", afterSaved.getId());

        // when
        List<PostsListResponseDto> responseDtos = this.postsService.findAllDesc();

        // then
        assertThat(responseDtos).isNotNull().isNotEmpty();
        assertThat(responseDtos.size()).isGreaterThan(0);
        Optional<PostsListResponseDto> opDto = responseDtos.stream()
                .filter(f -> f.getId().equals(afterSaved.getId()))
                .findAny();
        assertThat(opDto.get()).isNotNull();
    }

    @Test(expected = IllegalArgumentException.class)
    public void delete_test() {
        // given
        assertThat(this.beforeSaved).isNotNull();
        Long savedId = this.beforeSaved.getId();
        log.info("Before Saved Id: {}", savedId);

        // when
        this.postsService.delete(savedId);

        // then
        Long id = this.postsService.findById(savedId).getId();  // exception
    }
}
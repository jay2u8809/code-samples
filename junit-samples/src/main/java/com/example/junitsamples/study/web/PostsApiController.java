package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.service.posts.PostsService;
import com.example.junitsamples.study.web.dto.PostsUpdateRequestDto;
import com.example.junitsamples.study.web.dto.PostsResponseDto;
import com.example.junitsamples.study.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping(value = ApiEndPoint.PostApiController.SAVE)
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        log.info("save post: {}, {}", requestDto.getTitle(), requestDto.getAuthor());
        return this.postsService.save(requestDto);
    }

    @PutMapping(value = ApiEndPoint.PostApiController.UPDATE)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        log.info("update post: {}", id);
        return this.postsService.update(id, requestDto);
    }

    @GetMapping(value = ApiEndPoint.PostApiController.FIND_BY_ID)
    public PostsResponseDto findById(@PathVariable("id") Long id) {
        log.info("find post by id: {}", id);
        return this.postsService.findById(id);
    }

    @DeleteMapping(value = ApiEndPoint.PostApiController.DELETE)
    public Long delete(@PathVariable Long id) {
        log.info("delete post by id: {}", id);
        this.postsService.delete(id);
        return id;
    }
}

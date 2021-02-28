package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.individual.study.bootandaws.service.posts.PostsService;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsResponseDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsSaveRequestsDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController extends CommonExtends {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts/")
    public Long save(@RequestBody PostsSaveRequestsDto requestsDto) {

        return postsService.save(requestsDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestsDto) {

        return postsService.update(id, requestsDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}

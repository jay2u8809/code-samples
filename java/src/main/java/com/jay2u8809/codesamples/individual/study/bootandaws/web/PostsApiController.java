package com.jay2u8809.codesamples.individual.study.bootandaws.web;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.individual.study.bootandaws.service.posts.PostsService;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsResponseDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsSaveRequestsDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostsApiController extends CommonExtends {

    private final PostsService postsService;

    /**
     * 참고 : https://blog.naver.com/writer0713/221853596497
     * @param requestsDto
     * @return
     */
//    @PostMapping(value = "/posts/", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)   // key1=value&key2=value 형식. @RequestBody 를 사용하지 않는다.
    @PostMapping(value = "/posts/", consumes = MediaType.APPLICATION_JSON_VALUE)            // JSON 형식
    @ResponseBody
//    public ResponseEntity<?> save(@RequestBody Map<String, Object> param) {           // VO가 아닌 맵 형태로 받을 경우
    public ResponseEntity<?> save(@RequestBody PostsSaveRequestsDto requestsDto) {
//        String title = (String) param.get("title")
        Long savedPostSn = postsService.save(requestsDto);
        return ResponseEntity.ok().body(savedPostSn);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestsDto) {

        return postsService.update(id, requestsDto);
    }

    @GetMapping("/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable Long id) {

        postsService.delete(id);
        return id;
    }
}

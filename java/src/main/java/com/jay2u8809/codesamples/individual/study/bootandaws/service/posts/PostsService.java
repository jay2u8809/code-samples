package com.jay2u8809.codesamples.individual.study.bootandaws.service.posts;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.Posts;
import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.PostsRepository;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsResponseDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsSaveRequestsDto;
import com.jay2u8809.codesamples.individual.study.bootandaws.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id));

        return new PostsResponseDto(entity);
    }
}

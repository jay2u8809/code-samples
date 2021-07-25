package com.jay2u8809.codesamples.individual.study.bootandaws.web.posts.dto;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDt;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDt = entity.getModifiedDt();
    }
}

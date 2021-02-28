package com.jay2u8809.codesamples.individual.study.bootandaws.web.dto;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.Posts;

public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}

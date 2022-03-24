package com.example.junitsamples.study.web.dto;

import com.example.junitsamples.study.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;

    private String title;

    private String author;

    private LocalDateTime updatedDt;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.updatedDt = entity.getUpdatedDt();
    }
}

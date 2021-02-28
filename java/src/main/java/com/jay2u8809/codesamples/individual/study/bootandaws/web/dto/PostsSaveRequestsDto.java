package com.jay2u8809.codesamples.individual.study.bootandaws.web.dto;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestsDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestsDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(this.title)
                .content(this.content)
                .author(this.author)
                .build();
    }
}

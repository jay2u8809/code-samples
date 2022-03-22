package com.example.junitsamples.study.web.dto;

import com.example.junitsamples.study.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity 클래스와 유사하지만 반드시 Request/Response 용 DTO 를 이용
 *   Entity 클래스는 DB 와 맞닿은 핵심 클래스, Entity 클래스를 기준으로 테이블의 스키마가 변경됨
 *   DTO 는 View 를 위한 클래스이기에 변경이 잦고, DB 의 여러 테이블의 데이터를 조인하여 값을 세팅할 경우도 많음
 *   따라서, View Layer 와 DB Layer 의 분리를 위해라도 별도의 DTO 를 설정하여 사용
 */

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;

    private String content;

    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
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

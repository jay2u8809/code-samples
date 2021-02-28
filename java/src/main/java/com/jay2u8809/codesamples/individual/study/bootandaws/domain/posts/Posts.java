package com.jay2u8809.codesamples.individual.study.bootandaws.domain.posts;

import com.jay2u8809.codesamples.individual.study.bootandaws.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity                 // table과 링크될 클래스임을 나타냄, 언더스코어 네이밍 SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {

    // PK Filed
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙 IDENTITY : Auto Increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder        // 해당 클래스의 빌더 패턴 클래스 생성, 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

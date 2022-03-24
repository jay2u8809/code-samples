package com.example.junitsamples.study.domain.posts;

import com.example.junitsamples.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Setter 가 없는 이유
 *   Entity 에는 Setter 대신 필드의 값을 변경할 별도의 메소드를 만들어 사용한다.
 *     ex) setContent() => updateContent()
 *   클래스 필드의 변경 목적과 이유에 맞는 메소드를 만들어 변경한다.
 *   DB 에는 생성자를 통해 최종값을 넣어 저장한다.
 */

/**
 * @Entity
 *   테이블과 연결될 클래스임을 선언
 *   name 속성: 클래스명을 언더스코어(_)로 하는 테이블명을 매칭한다.
 *     ex) PostsHistory Class => posts_history Table
 * @NoArgsConstructor
 *   기본 생성자를 자동으로 추가한다.
 */
@Getter
@NoArgsConstructor
@Entity(name = "posts")
public class Posts extends BaseTimeEntity {

    /**
     * @Id
     *   테이블의 PK(Primary Key)
     * @GeneratedValue
     *   PK 의 생성 규칙
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * @Column
     *   이 어노테이션을 선언하지 않아도 클래스의 모든 필드는 컬럼이 된다.
     *   기본값 이외의 필요한 옵션이 있을 경우 선언하여 설정한다.
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    /**
     * @Builder
     *  클래스의 빌더 [패턴 클래스]를 형성
     *  생성자에 선언할 경우 생성자에 포함된 필드만 빌더에 포함한다.
     */
    @Builder
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

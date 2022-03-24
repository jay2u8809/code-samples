package com.example.junitsamples.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * DB Layer 접근자
 * Repository 어노테이션은 별도로 추가할 필요가 없다.
 * 기본적은 CRUD 메소드가 자동으로 생성된다.
 * Entity 클래스(Posts)와 기본 Entity Repository 는 같은 위치에 있어야 한다.
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {

    /**
     * @Entity(name=TABLE_NAME) 의 Table 명과 동일하게 작성하거나 테이블명을 생략했을 경우에는 클래스명을 그대로 쓴다.
     *   ex) @Entity(name = "posts"): @Query("SELECT p FROM posts p ORDER BY p.id DESC")
     *   ex) @Entity: @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
     * error : Validation failed for query for method public abstract java.util.List com.example.junitsamples.study.domain.posts.PostsRepository.findAllDesc()!
     *   resolved: https://teratail.com/questions/162673
     */
    @Query("SELECT p FROM posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

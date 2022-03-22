package com.example.junitsamples.study.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DB Layer 접근자
 * Repository 어노테이션은 별도로 추가할 필요가 없다.
 * 기본적은 CRUD 메소드가 자동으로 생성된다.
 * Entity 클래스(Posts)와 기본 Entity Repository 는 같은 위치에 있어야 한다.
 */

public interface PostsRepository extends JpaRepository<Posts, Long> {
}

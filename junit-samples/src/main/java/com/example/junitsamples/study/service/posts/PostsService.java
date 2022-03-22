package com.example.junitsamples.study.service.posts;

import com.example.junitsamples.study.domain.posts.Posts;
import com.example.junitsamples.study.domain.posts.PostsRepository;
import com.example.junitsamples.study.web.dto.PostsResponseDto;
import com.example.junitsamples.study.web.dto.PostsSaveRequestDto;
import com.example.junitsamples.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring 에서 Bean 을 주입받는 3가지 방법
 *   1) @Autowired
 *   2) setter
 *   3) Constructor: 가장 권장하는 방식, RequiredArgsConstructor 어노테이션을 사용하여 구현
 */

@RequiredArgsConstructor
@Slf4j
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return this.postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This post does not exist. id=" + id));
        log.debug("get updated post: {}", id);
        /**
         * this.postsRepository.save(UPDATED_ENTITY) 처리가 필요없는 이유
         *   JPA 의 영속성 컨텍스트(엔티티를 영구 저장하는 환경) 때문.
         *   JPA 의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있는지 여부에 따른다.
         *   JPA 의 엔티티 매니저(Entity Manager) 가 활성화 된 상태로 "트랜잭션 안에서 DB 로부터 데이터를 가져오면"
         *     데이터는 "영속성 컨텍스트가 유지" 된 상태이다
         *   이 상태에서 해당 데이터의 값을 변경하면, "트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영" 한다.
         *   엔티티 객체의 값만 변경해도 별도로 update 쿼리를 날리지 않아도 된다.
         *   이를 dirty checking 이라고 한다.
         *   this.postsRepository.findById(id):영속성 컨텍스트 유지 상태 => Posts 엔티티의 title, content 값 변경 => 트랜잭션이 끝날 때 DB 에 변경값 update
         */
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts posts = this.postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("This post does not exist. id=" + id));

        return new PostsResponseDto(posts);
    }
}

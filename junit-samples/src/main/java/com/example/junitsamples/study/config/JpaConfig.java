package com.example.junitsamples.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @EnableJpaAuditing
 *   JPA Auditing 을 활성화한다.
 *   최소 하나의 @Entity 클래스가 필요하다.
 *   이 어노테이션이 main() 에 있다면 @WebMvcTest 테스트에서 테스트 대상으로 main() 을 스캔하게 되고 (@SpringBootApplication)
 *   그 테스트스에서는 @Entity 가 없기 때문에 테스트에 실패한다. 따라서 별로의 클래스로 분리하는 편이 좋다.
 */

@Configuration
@EnableJpaAuditing
public class JpaConfig {
}

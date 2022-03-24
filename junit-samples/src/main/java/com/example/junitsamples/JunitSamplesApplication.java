package com.example.junitsamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @SpringBootApplication
 *   SpringBoot 의 자동설정, Spring Bean 읽기와 생성을 모두 자동으로 설정
 *   이 어노테이션이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 최상단에 위치함
 */
@SpringBootApplication
public class JunitSamplesApplication {

    public static void main(String[] args) {
        // SpringApplication.run() 으로 내장 WAS 실행
        // 항상 동일한 환경에서 스프링 부트를 배포할 수 있기 때문에 내장 WAS 를 사용하는 편이 좋다
        SpringApplication.run(JunitSamplesApplication.class, args);
    }

}

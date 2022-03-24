package com.example.junitsamples.study.web;

import com.example.junitsamples.study.common.ApiEndPoint;
import com.example.junitsamples.study.config.auth.SecurityConfig;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * @RunWith
 *   테스트 시, JUnit 에 내장된 실행자 외의 다른 실행자를 실행 (SpringRunner 실행자)
 *   스프링 부트 테스트와 JUnit 사이의 연결자 역할
 * @WebMvcTest
 *   Web(Spring MVC) 에 집중할 수 있는 어노테이션
 *   WebSecurityConfigurerAdapter, WebMvcConfigurer 등을 스캔
 *   Controller, ControllerAdvice 등의 어노테이션을 스캔, 사용 가능
 *   Service, Component, Repository 등의 어노테이션 사용 불가능
 * error: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaAuditingHandler': Cannot resolve reference to bean 'jpaMappingContext' while setting constructor argument; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'jpaMappingContext': Invocation of init method failed; nested exception is java.lang.IllegalArgumentException: JPA metamodel must not be empty!
 *   원인: test 중에 데이터베이스를 사용하지 않아서 스프링이 발생시키는 예외
 *         main method 가 있는 클래스에 @EnableJpaAuditing 를 추가하고 부터 발생했다
 *   해결: @MockBean(JpaMetamodelMappingContext.class) 어노테이션을 Mock test 클래스에 추가, https://stackoverflow.com/questions/41250177/getting-at-least-one-jpa-metamodel-must-be-present-with-webmvctest
 */
@MockBean(JpaMetamodelMappingContext.class)
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = com.example.junitsamples.study.web.StudyHomeController.class,
            excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class))
public class StudyHomeControllerTest {

    /**
     * MockMvc
     *   Web API 테스트에 사용
     *   스프링 MVC 테스트의 시작점
     *   이 클래스를 통해 Http get, post 등에 대한 API 테스트가 가능
     */
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles = "USER")
    public void hello() throws Exception {
        String hello = "hello";

        mvc.perform(MockMvcRequestBuilders.get(ApiEndPoint.StudyHomeController.HELLO))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(hello));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        /**
         * param(): QueryString 의 파라미터 설정, String 만 가능
         *   localhost:8080/hello/dto?name=hello&amount=1000
         * jsonPath(): JSON 응답값을 필드별로 검증할 수 있음, $ 를 기준으로 필드명을 명시한다.
         */
        mvc.perform(MockMvcRequestBuilders.get(ApiEndPoint.StudyHomeController.HELLO_DTO).param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is(name)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.amount", Matchers.is(amount)));
    }
}
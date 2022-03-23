package com.example.junitsamples.study.config.auth;

import com.example.junitsamples.study.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @EnableWebSecurity
 *   Spring Security 설정들을 활성화 시킴
 */

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        /**
         * csrf() ~ disable(): h2-console 화면을 사용하기 위해 옵션을 disable 한다.
         * authorizeRequests(): URL 별 권한 관리 설정
         *   antMatchers(): 권한 관리 대상 지정, 전체 열람권한(permitAll()), USER 권한(hasRole())
         *   anyRequest(): 설정된 값들 이외의 나머지 모든 URL 은 인증된 사용자들만 허용(authenticated())
         * logout(): 로그아웃 기능에 대한 설정, 로그아웃 성공시 "/" 로 이동
         * oauth2Login(): OAuth 2 로그인 기능에 대한 설정
         *   userInfoEndpoint(): OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정
         *   userService(): 소셜 로그인 성공 시 후속 처리를 할 인터페이스 구현체 등록, 소셜 서비스 서버에서 가져온 사용자 정보에 추가하고 싶은 기능을 명시할 수 있음
         */
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOAuth2UserService);
    }

}

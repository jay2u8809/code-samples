package com.example.junitsamples.study.config.auth;

import com.example.junitsamples.study.config.auth.dto.OAuthAttributes;
import com.example.junitsamples.study.config.auth.dto.SessionUser;
import com.example.junitsamples.study.domain.user.User;
import com.example.junitsamples.study.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        /**
         * 현재 로그인 진행 중인 서비스를 구분
         * 어떤 SNS 서비스로 로그인하는지 구분한다.
         */
        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();

        /**
         * OAuth2 로그인 진행 시 key 가 되는 필드값, Primary key 와 같은 역할
         * 구글은 기본적으로 코드를 지원하지만(sub) 네이버나 카카오 등은 기본 지원하지 않는다.
         */
        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        /**
         * OAuth2UserService 를 통해 가져온 OAuth2User 의 attribute 를 담을 클래스
         */
        OAuthAttributes  attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        /**
         * SessionUser: 세션에 사용자 정보를 지정하기 위한 dto
         * User Entity 를 그대로 사용할 경우, 다른 객체와의 연관 관계(@ManyToMany, @OneToMany)로 인해
         * 직렬화 대상에 자식 객체들까지 포함되기 때문에 따로 dto 를 만들어 사용한다.
         */
        this.httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), attributes.getAttributes(), attributes.getNameAttributeKey()
        );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = this.userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return this.userRepository.save(user);
    }
}

package com.example.junitsamples.study.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Target(ElementType.PARAMETER)
 *   이 어노테이션을 생성할 수 있는 위치 지정
 *   현재는 메소드의 파라미터에서만 사용 가능
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}

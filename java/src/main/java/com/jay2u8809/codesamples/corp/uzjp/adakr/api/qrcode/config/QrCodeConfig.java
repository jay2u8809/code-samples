package com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode.config;

import com.jay2u8809.codesamples.common.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring Boot에서 properties 값 주입받기
 *   https://bottom-to-top.tistory.com/46
 * @ConfigurationProperties 사용법 - 삽질중인 개발자
 *   https://programmer93.tistory.com/47
 * yml에서 데이터 가져오기 및 스프링부트 디비 2개 이상 연결하는 법
 *   https://koogood.tistory.com/23
 */

@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "open-api.qrcode")
public class QrCodeConfig {
    private String apiUri;
}

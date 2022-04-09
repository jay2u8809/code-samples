package com.jay2u8809.codesamples.common.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Spring Boot @ConfigurationProperties 테스트
 *   https://recordsoflife.tistory.com/436
 * [Spring] @TestConfiguration
 *   https://csy7792.tistory.com/335
 */

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(value = ApplicationYamlData.class)
//@TestPropertySource("classpath:application.yml")
class ApplicationYamlDataTest {

    @Autowired
    private ApplicationYamlData data;

    @Test
    void get_yaml_test() {
        System.out.println(data.getPort());
    }
}
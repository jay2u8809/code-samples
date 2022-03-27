package com.jay2u8809.codesamples.common.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

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
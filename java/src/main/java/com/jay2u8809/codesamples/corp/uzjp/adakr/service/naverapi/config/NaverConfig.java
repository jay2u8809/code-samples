package com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.config;

import com.jay2u8809.codesamples.common.config.YamlPropertySourceFactory;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.config.MapConfigDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(value = "open-api.naver")
public class NaverConfig {
    private boolean dummy;
    private MapConfigDto map;
}

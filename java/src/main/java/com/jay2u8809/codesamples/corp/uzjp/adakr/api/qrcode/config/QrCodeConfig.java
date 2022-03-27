package com.jay2u8809.codesamples.corp.uzjp.adakr.api.qrcode.config;

import com.jay2u8809.codesamples.common.config.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Configuration
@PropertySource(value = "classpath:application.yml", factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix = "open-api.qrcode")
public class QrCodeConfig {
    private String apiUri;
}

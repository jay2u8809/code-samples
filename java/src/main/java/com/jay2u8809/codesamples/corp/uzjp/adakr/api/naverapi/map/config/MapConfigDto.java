package com.jay2u8809.codesamples.corp.uzjp.adakr.api.naverapi.map.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MapConfigDto {

    private GeoCoding geoCoding;

    @Getter
    @Setter
    public static class GeoCoding {
        private String apiUrl;
        private String clientId;
        private String clientSecret;
    }
}

package com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map;

import com.jay2u8809.codesamples.common.CommonExtends;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.config.NaverConfig;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.config.MapConfigDto;
import com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.dto.GeoCodingResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class NaverMapService extends CommonExtends {

    private final NaverConfig naverConfig;

    private final String CLIENT_ID_HEADER_NAME = "X-NCP-APIGW-API-KEY-ID";
    private final String CLIENT_SECRET_HEADER_NAME = "X-NCP-APIGW-API-KEY";

    /**
     * Address -> Geocode
     * @param address
     */
    public GeoCodingResponseDto getGeoCodeInfo(String address) {

        if (address == null || address.length() == 0) {
            throw new IllegalArgumentException("Address cannot be empty");
        }

        // config
        MapConfigDto.GeoCoding geoCodingConfig = this.naverConfig.getMap().getGeoCoding();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(this.CLIENT_ID_HEADER_NAME, geoCodingConfig.getClientId());
        headers.add(this.CLIENT_SECRET_HEADER_NAME, geoCodingConfig.getClientSecret());

        String apiUri = geoCodingConfig.getApiUrl();
        URI uri = UriComponentsBuilder
                .fromUriString(apiUri)
                .queryParam("address", address)
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUri();
        logger.debug("Naver GeoCoding Request URL: {}", uri);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<GeoCodingResponseDto> result = restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<>(headers), GeoCodingResponseDto.class);
            logger.info("Generate Qr Code Result: {}", result);
            return result.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public MapConfigDto getMapConfig() {
        return this.naverConfig.getMap();
    }
}

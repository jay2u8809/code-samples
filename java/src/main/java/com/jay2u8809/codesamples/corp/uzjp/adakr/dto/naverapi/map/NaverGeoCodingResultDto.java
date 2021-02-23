package com.jay2u8809.codesamples.corp.uzjp.adakr.dto.naverapi.map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Naver Maps API(GeoCoding API) DTO
 * Convert JSON format results to JAVA objects
 * Reference：https://apidocs.ncloud.com/ko/ai-naver/maps_geocoding/geocode/
 */
@Getter
@Setter
public class NaverGeoCodingResultDto {

    // Search result status code (required) : OK -> 200, INVALID_REQUEST -> 400, SYSTEM_ERROR -> 500
    @JsonProperty("status")
    private String status;
    // Search Meta Data
    @JsonProperty("meta")
    private GeoCodingMetaInfo metaInfo;
    // Address search result list
    @JsonProperty("addresses")
    private List<NaverGeoCodingAddress> addresses;
    // Error Messages
    @JsonProperty("errorMessage")
    private String errorMessage;

    @Getter
    @Setter
    public static class GeoCodingMetaInfo {
        // Number of search results (overall)
        private Integer totalCount;
        // Page number
        private Integer page;
        // Number of results on page
        private Integer count;
    }
}

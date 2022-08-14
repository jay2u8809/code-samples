package com.jay2u8809.codesamples.corp.uzjp.adakr.service.naverapi.map.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Naver Maps API(GeoCoding API) DTO
 * Convert JSON format results to JAVA objects
 * Reference：https://api.ncloud-docs.com/docs/ai-naver-mapsgeocoding-geocode
 */
@Getter
@NoArgsConstructor
public class GeoCodingResponseDto {

    /**
     * Search result status code (required)
     *   OK -> 200
     *   INVALID_REQUEST -> 400
     *   SYSTEM_ERROR -> 500
     */
    @JsonProperty("status")
    private String status;

    /**
     * Search Meta Data
     */
    @JsonProperty("meta")
    private GeoCodingMetaDto meta;

    /**
     * Address search result list
     */
    @JsonProperty("addresses")
    private List<GeoCodingAddress> addresses;

    /**
     * Error Messages
     */
    @JsonProperty("errorMessage")
    private String errorMessage;

    @Builder
    public GeoCodingResponseDto(
            String status,
            GeoCodingMetaDto meta,
            List<GeoCodingAddress> addresses,
            String errorMessage
    ) {
        this.status = status;
        this.meta = meta;
        this.addresses = addresses;
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "GeoCodingResponseDto{" +
                "status='" + this.status + '\'' +
                ", meta=" + this.meta +
                ", addresses=" + this.addresses +
                ", errorMessage='" + this.errorMessage + '\'' +
                '}';
    }
}

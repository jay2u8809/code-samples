package com.jay2u8809.codesamples.corp.uzjp.adakr.api.naverapi.map.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * NAVER Geocoding Api Address Information Results
 * @JsonIgnoreProperties
 *   무시할 속성이나 속성 목록을 표시하는 데 사용 (https://cheese10yun.github.io/jackson-annotation-03/)
 */
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(value = { "addressElements", "distance" })
public class GeoCodingAddress {

    /**
     * Road name-based Address
     */
    private String roadAddress;

    /**
     * The Land Lot-Based Address
     */
    private String jibunAddress;

    /**
     * English Address
     */
    private String englishAddress;

    /**
     * Other address information
     */
    private List<GeoCodingAddressElements> addressElements;

    /**
     * Longitude information
     */
    private String x;

    /**
     * Latitude information
     */
    private String y;

    /**
     * Distance from search center coordinates (unit: meters)
     */
    private String distance;

    @Builder
    public GeoCodingAddress(
            String roadAddress,
            String jibunAddress,
            String englishAddress,
            List<GeoCodingAddressElements> addressElements,
            String x,
            String y,
            String distance
    ) {
        this.roadAddress = roadAddress;
        this.jibunAddress = jibunAddress;
        this.englishAddress = englishAddress;
        this.addressElements = addressElements;
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GeoCodingAddress{" +
                "roadAddress='" + roadAddress + '\'' +
                ", jibunAddress='" + jibunAddress + '\'' +
                ", englishAddress='" + englishAddress + '\'' +
                ", addressElements=" + addressElements +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }

    @Getter
    @NoArgsConstructor
    public static class GeoCodingAddressElements {
        private List<String> types;
        private String longName;
        private String shortName;
        private String code;

        @Builder
        public GeoCodingAddressElements(
                List<String> types,
                String longName,
                String shortName,
                String code
        ) {
            this.types = types;
            this.longName = longName;
            this.shortName = shortName;
            this.code = code;
        }

        @Override
        public String toString() {
            return "GeoCodingAddressElements{" +
                    "types=" + types +
                    ", longName='" + longName + '\'' +
                    ", shortName='" + shortName + '\'' +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}

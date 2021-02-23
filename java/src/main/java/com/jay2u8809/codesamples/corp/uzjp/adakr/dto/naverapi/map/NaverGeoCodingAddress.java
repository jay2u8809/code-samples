package com.jay2u8809.codesamples.corp.uzjp.adakr.dto.naverapi.map;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * NAVER Geocoding Api Address Information Results
 */
@Getter
@Setter
//@JsonIgnoreProperties(value = { "addressElements", "distance" })    // Other address information and distance: Not required
public class NaverGeoCodingAddress {

    // Road name-based Address
    private String roadAddress;
    // The Land Lot-Based Address
    private String jibunAddress;
    // English Address
    private String englishAddress;
    // Other address information
    private List<GeoCodingAddressElements> addressElements;
    // Longitude information
    private String x;
    // Latitude information
    private String y;
    // Distance from search center coordinates (unit: meters)
    private String distance;

    @Getter
    @Setter
    public static class GeoCodingAddressElements {
        private List<String> types;
        private String longName;
        private String shortName;
        private String code;
    }

    @Override
    public String toString() {

        return new StringBuilder(NaverGeoCodingAddress.class.getName()).append(" [ ")
                        .append("ROAD ADDRESS : ").append(this.roadAddress).append(" ")
                        .append("JIBUN ADDRESS : ").append(this.jibunAddress).append(" ")
                        .append("ENGLISH ADDRESS : ").append(this.englishAddress).append(" ")
                        .append("ADDRESS ELEMENTS : ").append(this.addressElements).append(" ")
                        .append("LONGITUDE : ").append(this.x).append(" ")
                        .append("LATITUDE : ").append(this.y).append(" ")
                        .append("DISTANCE : ").append(this.distance).append(" ")
                    .append("]")
                    .toString();
    }
}

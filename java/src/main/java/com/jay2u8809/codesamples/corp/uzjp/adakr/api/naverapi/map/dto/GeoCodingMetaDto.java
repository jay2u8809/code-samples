package com.jay2u8809.codesamples.corp.uzjp.adakr.api.naverapi.map.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GeoCodingMetaDto {

    /**
     * Number of search results (overall)
     */
    private Integer totalCount;

    /**
     * Page number
     */
    private Integer page;

    /**
     * Number of results on page
     */
    private Integer count;

    @Builder
    public GeoCodingMetaDto(Integer totalCount, Integer page, Integer count) {
        this.totalCount = totalCount;
        this.page = page;
        this.count = count;
    }

    @Override
    public String toString() {
        return "GeoCodingMetaDto{" +
                "totalCount=" + totalCount +
                ", page=" + page +
                ", count=" + count +
                '}';
    }
}

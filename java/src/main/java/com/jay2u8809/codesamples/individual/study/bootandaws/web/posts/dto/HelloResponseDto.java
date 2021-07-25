package com.jay2u8809.codesamples.individual.study.bootandaws.web.posts.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {

    private final String name;

    private final int amount;

    private String address;
}

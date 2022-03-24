package com.example.junitsamples.study.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SnsProvider {

    NAVER("naver", "Naver"),
    GOOGLE("google", "Google");

    private final String key;
    private final String title;
}

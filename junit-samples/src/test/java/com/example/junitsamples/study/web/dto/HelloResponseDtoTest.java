package com.example.junitsamples.study.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.junit.Assert.*;

public class HelloResponseDtoTest {

    @Test
    public void lombok_function_test() {
        // given
        String name = "test";
        int amount = 1000;

        // when: variable not initialized in the default constructor error(https://deeplify.dev/back-end/spring/lombok-required-args-constructor-initialize-error)
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
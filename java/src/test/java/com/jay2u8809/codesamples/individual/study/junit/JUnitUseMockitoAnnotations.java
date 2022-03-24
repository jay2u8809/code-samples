package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.individual.study.junit.member.JUnitMemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class JUnitUseMockitoAnnotations {

    @Mock
    JUnitMemberService mockMemberService;

    @BeforeEach
    void before_each() {
        // set mock annotations
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("Stubbing Test: String")
    @Test
    void mock_test_stubbing_string() {
        String uuid = UUID.randomUUID().toString();
        String result = this.mockMemberService.getMemberSnById(uuid);
        Assertions.assertAll(
                () -> Assertions.assertNull(result),
                () -> Assertions.assertThrows(NullPointerException.class, () -> result.length())
        );
    }
}

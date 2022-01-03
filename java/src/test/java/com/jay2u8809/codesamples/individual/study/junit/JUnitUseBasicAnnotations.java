package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.code.MemberStatus;
import org.junit.jupiter.api.*;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)       // Change Test Method Name : create_test => create test
class JUnitUseBasicAnnotations {

    @DisplayName("Make Create Test")        // Change Test Method Name : create_test => Make Create Test
    @Test
    void create_test() {
        System.out.println("CREATE TEST");
        JUnitStudy study = new JUnitStudy(-10);

        // 도중에 Test 가 실패하더라도 다른 각각의 Test 를 실행하여 진행할 수 있다.(Fail 테스트 케이스이므로 주석처리)
//        assertAll(
//                () -> assertNotNull(study),
//                // Test 의 성공, 실패와 상관없이 message 가 실행된다.
//                () -> assertEquals(MemberStatus.Normal, study.getStatus(), "MemberStatus must be " + MemberStatus.Normal),
//                // Lambda 형식 또는 Supplier 의 경우 Test 가 실패한 경우에만 message 가 실행된다.
//                () -> assertEquals(MemberStatus.Normal, study.getStatus(), () -> "MemberStatus must be " + MemberStatus.Normal),
//                () -> assertEquals(MemberStatus.Normal, study.getStatus(), new Supplier<String>() {
//                    @Override
//                    public String get() {
//                        return "MemberStatus must be " + MemberStatus.Normal;
//                    }
//                }),
//                () -> assertTrue(study.getLimit() > 0, "Bigger Than Zero")
//        );

        // 도중에 하나의 Test 가 실패할 경우 그 다음 Test 를 진행할 수 없다.
//        assertEquals(MemberStatus.Normal, study.getStatus(), () -> "MemberStatus must be " + MemberStatus.Normal);
//        assertTrue(study.getLimit() > 0, "Bigger Than Zero");
    }

    @Disabled
    @Test
    void process_test() {
        System.out.println("PROCESS TEST (SKIP)");
    }

    @BeforeAll
    static void before_all_test() {
        System.out.println("0. BEFORE ALL TEST");
    }

    @AfterAll
    static void after_all_test() {
        System.out.println("999. AFTER ALL TEST");
    }

    @BeforeEach
    void before_each_test() {
        System.out.println("BEFORE EACH TEST");
    }

    @AfterEach
    void after_each_test() {
        System.out.println("AFTER EACH TEST");
    }
}

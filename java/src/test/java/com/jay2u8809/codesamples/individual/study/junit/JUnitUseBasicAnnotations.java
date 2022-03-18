package com.jay2u8809.codesamples.individual.study.junit;

import org.junit.jupiter.api.*;

/**
 * test 케이스의 메소드 명은 CamelCase보다 언더바 형식으로 한다.
 * DisplayNameGeneration 어노테이션은 클래스, 메소드 모두에 사용가능하다.
 */

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)   // Change Test Method Name : create_test => create test
class JUnitUseBasicAnnotations {

    /**
     * 테스트 시작 전에 한번만 실행, @BeforeClass
     * static, void 로 생성
     */
    @BeforeAll
    static void before_all() {
        System.out.println("0. BEFORE ALL TEST");
    }

    /**
     * 테스트 종료 후에 한번만 실행, @AfterClass
     * static, void 로 생성
     */
    @AfterAll
    static void after_all() {
        System.out.println("999. AFTER ALL TEST");
    }

    /**
     * 각 테스트 케이스의 시작 전에 실행, @Before
     */
    @BeforeEach
    void before_each() {
        System.out.println("BEFORE EACH TEST");
    }

    /**
     * 각 테스트 케이스의 종료 후에 실행, @After
     */
    @AfterEach
    void after_each() {
        System.out.println("AFTER EACH TEST");
    }

    /**
     * DisplayName("TEST NAME") : Change Test Method Name, create_test => Make Create Test
     */
    @DisplayName("Enabled Test")
    @Test
    void enabled_test() {
        System.out.println("CREATE TEST");
    }

    /**
     * test skip case, @Ignored
     */
    @DisplayName("Disabled Test")
    @Disabled
    @Test
    void disabled_test() {
        System.out.println("PROCESS TEST (SKIP)");
    }
}

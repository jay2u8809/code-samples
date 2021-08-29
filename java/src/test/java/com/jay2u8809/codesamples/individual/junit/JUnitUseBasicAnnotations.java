package com.jay2u8809.codesamples.individual.junit;

import org.junit.jupiter.api.*;

class JUnitUseBasicAnnotations {

    @Test
    void create() {
        System.out.println("CREATE TEST");
    }

    @Disabled
    @Test
    void process() {
        System.out.println("PROCESS TEST (SKIP)");
    }

    @BeforeAll
    static void beforeAllTest() {
        System.out.println("0. BEFORE ALL TEST");
    }

    @AfterAll
    static void afterAllTest() {
        System.out.println("999. AFTER ALL TEST");
    }

    @BeforeEach
    void beforeEachTest() {
        System.out.println("BEFORE EACH TEST");
    }

    @AfterEach
    void afterEachTest() {
        System.out.println("AFTER EACH TEST");
    }
}

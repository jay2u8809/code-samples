package com.jay2u8809.codesamples.individual.study.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

/**
 * 조건에 따른 테스트 실행
 */

class JUnitUseCondition {

    /**
     * check env, properties
     */
    @DisplayName("Check Env and Properties Test")
    @Test
    void condition_test_0() {
        System.getenv().forEach((String key, String value) -> System.out.println("TEST ENV: " + key + ": " + value));
        System.getProperties().forEach((key, value) -> System.out.println("PROPERTIES: " + key + ": " + value));
    }

    /**
     * assumeTrue 메소드의 결과가 true인 경우 아래의 코드가 실행
     */
    @DisplayName("assumeTrue OK Test")
    @Test
    void condition_test_1() {
        String testProperty = System.getProperty("os.name");
        // pass
        assumeTrue("Mac OS X".equalsIgnoreCase(testProperty));
        System.out.println("Pass case");
        assertEquals(8, testProperty.length());
    }

    /**
     * assumeTrue 메소드의 결과가 false인 경우 아래의 코드가 실행되지 않음
     */
    @DisplayName("assumeTrue NG Test")
    @Test
    void condition_test_2() {
        String testProperty = System.getProperty("os.name");
        // ignore
        assumeTrue("Android".equalsIgnoreCase(testProperty));
        System.out.println("Ignore case");
        assertEquals(7, testProperty.length());
    }

    /**
     * assumingThat 메소드 내에서 블럭화 시켜 테스트 진행
     * 첫 번째 파라미터의 boolean이 true인 경우, lambda를 실행
     */
    @DisplayName("AssumingThat OK Test")
    @Test
    void condition_test_3() {
        String testProperty = System.getProperty("os.name");
        // pass
        assumingThat("Mac OS X".equalsIgnoreCase(testProperty), () -> {
            System.out.println("Pass case");
            assertEquals(8, testProperty.length());
        });
    }

    /**
     * assumingThat 메소드 내에서 블럭화 시켜 테스트 진행
     * 첫 번째 파라미터의 boolean이 false인 경우, lambda를 실행하지 않음
     */
    @DisplayName("AssumingThat NG Test")
    @Test
    void condition_test_4() {
        String testProperty = System.getProperty("os.name");
        // ignore
        assumingThat("Android".equalsIgnoreCase(testProperty), () -> {
            System.out.println("Ignore case");
            assertEquals(7, testProperty.length());
        });
    }

    // ==== 어노테이션을 이용하는 방법 ====

    @DisplayName("EnabledOnOs Annotation: OS, OK")
    @EnabledOnOs({OS.MAC, OS.LINUX})
//    @DisabledOnOs(OS.WINDOWS)
    @Test
    void condition_test_5() {
        String testProperty = System.getProperty("os.name");
        System.out.println("Ignore case");
        assertEquals(8, testProperty.length());
    }

    @DisplayName("EnabledOnOs Annotation: OS, NG")
//    @EnabledOnOs(OS.WINDOWS)
    @DisabledOnOs({OS.MAC, OS.LINUX})
    @Test
    void condition_test_6() {
        String testProperty = System.getProperty("os.name");
        System.out.println("Ignore case");
        assertEquals(7, testProperty.length());
    }

    @DisplayName("EnabledIfSystemProperty Annotation: OK")
    @EnabledIfSystemProperty(named = "os.name", matches = "Mac OS X")
    @Test
    void condition_test_7() {
        String testProperty = System.getProperty("os.name");
        System.out.println("Pass case");
        assertEquals(8, testProperty.length());
    }
}

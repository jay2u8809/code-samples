package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)     // 메소드 실행 순서와 상관없지만 각 테스트 메소드들을 연결하여 실행하고 싶다면 설정
//@TestMethodOrder(MethodOrderer.MethodName.class)    // 테스트 메소드 실행 순서: 메소드명 순서
//@TestMethodOrder(MethodOrderer.DisplayName.class)   // 테스트 메소드 실행 순서: @DisplayName(내용) 순서, 숫자 > 영대문자 > 영소문자
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)   // 테스트 메소드 실행 순서: @Order(번호) 순서, 1 > 2 > 3
@TestMethodOrder(MethodOrderer.Random.class)    // 테스트 메소드 실행 순서: 랜덤
public class JUnitUserTestOrder extends CommonExtends {

//    @Order(5)
//    @DisplayName("a")
    @Test
    void test_1() {
        logger.info("TEST 001");
    }

//    @Order(4)
//    @DisplayName("4")
    @Test
    void test_2() {
        logger.info("TEST 002");
    }

//    @Order(3)
//    @DisplayName("A")
    @Test
    void test_3() {
        logger.info("TEST 003");
    }

//    @Order(2)
//    @DisplayName("3")
    @Test
    void test_4() {
        logger.info("TEST 004");
    }

//    @Order(1)
//    @DisplayName("5")
    @Test
    void test_5() {
        logger.info("TEST 005");
    }
}

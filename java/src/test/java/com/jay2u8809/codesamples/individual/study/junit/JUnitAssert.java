package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.code.MemberStatus;
import com.jay2u8809.codesamples.individual.study.junit.domain.JUnitStudy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 기본적으로 spring-boot-start-test 에는 assertj 나 hamcrest 를 포함한다.
 */
public class JUnitAssert {

    /**
     * 테스트의 데이터 체크 및 에러 발생시 에러 메시지 생성 방법
     */
    @DisplayName("Basic and error msg Test")
    @Test
    void assertions_test_1() {
        JUnitStudy study = new JUnitStudy();
        assertNotNull(study);
        // String 리터럴을 이용한 방법: Error가 발생하지 않아도 Msg를 생성한다.
        assertEquals(MemberStatus.Normal, study.getStatus(), "생성 후 처음은 " + MemberStatus.Normal);
        // Lambda를 이용한 방법: Error가 발생하지 않으면 Msg를 생성하지 않는다. (좀 더 효율적인 성능)
        assertEquals(MemberStatus.Normal, study.getStatus(), () -> "생성 후 처음은 " + MemberStatus.Normal);
        // Supplier 객체를 이용한 방법: Error가 발생하지 않으면 Msg를 생성하지 않는다. (좀 더 효율적인 성능)
        assertEquals(MemberStatus.Normal, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "MemberStatus must be " + MemberStatus.Normal;
            }
        });
    }

    /**
     * 테스트 도중의 에러가 발생해도 테스트를 끝까지 진행하는 방법
     * 테스트가 도중에 실패하먄 전체 테스트가 진행되지 않고 멈추게 되는데, 다른 각각의 Test 를 실행하여 진행할 수 있다
     */
    @DisplayName("Error control Test: assertAll")
    @Test
    void assertions_test_2() {
        JUnitStudy study = new JUnitStudy();
        assertAll(
                // Pass
                () -> assertNotNull(study),
                // Fail
                () -> assertEquals(MemberStatus.Dormancy, study.getStatus(), () -> "생성 후 처음은 " + MemberStatus.Normal),
                // Pass
                () -> assertFalse(10 < study.getLimit(), () -> "생성 후 처음은 " + study.getLimit())
        );
    }

    /**
     * 알맞는 예외가 발생하는지 체크
     */
    @DisplayName("Exception Check Test: assertThrows")
    @Test
    void assertions_test_3() {
        // check exception
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new JUnitStudy(-10, null));
        // check error msg
        assertEquals("limit >= 0", exception.getMessage());
    }

    /**
     * 정해진 시간 안에 테스트가 끝나는지 체크
     * 기준 시간이 지나도 테스트가 끝날 때까지 진행
     * Lambda 함수를 별도의 thread 에서 진행하기 떄문에 Lambda 함수 내에 ThreadLocal 을 사용하고 있다면 예상치 못한 에러가 발생할 수 있다.
     * (ThreadLocal 은 다른 Thread 와 공유가 되지 않아 테스트에서 스프링의 트랙잭션 설정이 제대로 적용되지 않을 수 있음)
     * 스프링 트랙잭션은 기본적으로 ThreadLocal
     */
    @DisplayName("Timeout Check Test: assertTimeout")
    @Test
    void assertions_test_4() {
        // Fail의 경우Test Total Time: 300ms
        assertTimeout(Duration.ofMillis(100), () -> {
            JUnitStudy study = new JUnitStudy();
//            Thread.sleep(300);    // fail
            Thread.sleep(80);   // pass
        });
    }

    /**
     * 정해진 시간 안에 테스트가 끝나는지 체크
     * 기준 시간이 지나면 테스트 종료
     * Lambda 함수를 별도의 thread 에서 진행하기 떄문에 Lambda 함수 내에 ThreadLocal 을 사용하고 있다면 예상치 못한 에러가 발생할 수 있다.
     * (ThreadLocal 은 다른 Thread 와 공유가 되지 않아 테스트에서 스프링의 트랙잭션 설정이 제대로 적용되지 않을 수 있음)
     * 스프링 트랙잭션은 기본적으로 ThreadLocal
     */
    @DisplayName("Timeout Check Test: assertTimeoutPreemptively")
    @Test
    void assertions_test_5() {
        // Fail 의 경우 Test Total Time: 100ms
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            JUnitStudy study = new JUnitStudy();
//            Thread.sleep(300);    // fail
            Thread.sleep(80);  // pass
        });
    }

    /**
     * AssertJ
     */
    @DisplayName("AssertJ Test")
    @Test
    void assertions_test_6() {
        JUnitStudy study = new JUnitStudy(10, null);
        assertThat(study.getLimit()).isGreaterThan(5);
    }
}

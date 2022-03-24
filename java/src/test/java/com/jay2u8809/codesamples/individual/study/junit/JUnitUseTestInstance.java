package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


/**
 * JUnit 의 테스트 클래스의 각 메소드들은 각기 다른 인스턴스로 생성된다.
 * 테스트 메소드 간의 의존성을 없애고 독립적으로 실행 시키기 위해 메소드 당 인스턴스를 생성하여 실행한다.
 * @TestInstance(TestInstance.Lifecycle.PER_METHOD) or "설정하지 않음" 의 경우, 테스트 클래스는 각 메소드 별로 인스턴스화 된다.
 * @TestInstance(TestInstance.Lifecycle.PER_CLASS) 의 경우, 테스트 클래스가 하나의 인스턴스로 생성된다.
 *   TestInstance.Lifecycle.PER_CLASS 의 경우는 @BeforeAll, @AfterAll 의 메소드가 static 일 필요가 없다.
 *   여러 인스턴스에서 공통으로 사용되지 않아도 되기 때문에 static 메소드가 아니어도 된다.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JUnitUseTestInstance extends CommonExtends {

    int cnt = 0;

    /**
     * TestInstance.Lifecycle.PER_METHOD or "설정하지 않음"
     *   test_instance_1() ~ test_instance_3() 의 hashCode 의 값은 다르고, cnt의 값은 0으로 동일하다.
     * TestInstance.Lifecycle.PER_CLASS
     *   test_instance_1() ~ test_instance_3() 의 hashCode 의 값은 동일하고, cnt의 값은 0, 1, 2로 증가한다.
     */
    @Test
    void test_instance_1() {
        logger.info("{}", this);
        logger.info("{}", this.hashCode());
        logger.info("{}", cnt++);
    }

    @Test
    void test_instance_2() {
        logger.info("{}", this);
        logger.info("{}", this.hashCode());
        logger.info("{}", cnt++);
    }

    @Test
    void test_instance_3() {
        logger.info("{}", this);
        logger.info("{}", this.hashCode());
        logger.info("{}", cnt++);
    }

    @BeforeAll
    void before_all() {
        System.out.println("BEFORE ALL");
    }

    @AfterAll
    void after_all() {
        System.out.println("AFTER ALL");
    }
}

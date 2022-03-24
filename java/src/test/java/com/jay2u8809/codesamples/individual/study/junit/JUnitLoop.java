package com.jay2u8809.codesamples.individual.study.junit;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

public class JUnitLoop extends CommonExtends {

    @DisplayName("10 times repeat test: no param")
    @RepeatedTest(10)   // 10 times
    void repeat_test_1() {
        logger.info("repeat");
    }

    @DisplayName("10 times repeat test")
    @RepeatedTest(10)   // 10 times
    void repeat_test_2(RepetitionInfo repetitionInfo) {
        logger.info("Current / Total: " + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("10 times repeat and test case name test")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")   // times, test case name
    void repeat_test_3(RepetitionInfo repetitionInfo) {
        logger.info("Current / Total: " + repetitionInfo.getCurrentRepetition() + " / " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("parameterize (strings) test")
    @ParameterizedTest()
    @ValueSource(strings = {"param1", "param2", "param3"})
    void repeat_test_4(String str) {
        logger.info(str);
    }

    @DisplayName("parameterize (ints) test")
    @ParameterizedTest()
    @ValueSource(ints = {1, 2, 3})
    void repeat_test_5(int num) {
        logger.info("{}", num);
    }

    @DisplayName("parameterize test case name test")
    @ParameterizedTest(name = "{index}, {displayName}, parameter = {arguments}")    // test case name
    @ValueSource(strings = {"param1", "param2", "param3", "value4"})
    void repeat_test_6(String str) {
        logger.info(str);
    }

    @DisplayName("parameterize empty & null test")
    @ParameterizedTest(name = "{index}, {displayName}, parameter = {arguments}")    // test case name
    @ValueSource(strings = {"param1", "param2", "param3", "value4"})
    @EmptySource
    @NullSource
//    @NullAndEmptySource
    void repeat_test_7(String str) {
        logger.info(str);
    }
}

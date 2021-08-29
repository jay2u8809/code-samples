package com.jay2u8809.codesamples.individual.algorithm.exam.weatherInfo;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FindMinNumWithoutZeroTest extends CommonExtends {

    private FindMinNumWithoutZero findMinNumWithoutZero;

    @BeforeEach
    public void init() {
        this.findMinNumWithoutZero = new FindMinNumWithoutZero();
    }

    @Test
    public void solutionTest() {

        String test1Sample = "402";
        String test1 = this.findMinNumWithoutZero.makeNumberByString(test1Sample);

        logger.info("Test1 Result : {}", test1);
    }
}

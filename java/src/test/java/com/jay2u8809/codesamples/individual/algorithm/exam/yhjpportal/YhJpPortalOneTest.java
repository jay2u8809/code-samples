package com.jay2u8809.codesamples.individual.algorithm.exam.yhjpportal;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class YhJpPortalOneTest extends CommonExtends {

    private YhJpPortalOne yhJpPortalOne;

    @BeforeEach
    public void init() {
        this.yhJpPortalOne = new YhJpPortalOne();
    }

    @Test
    public void solutionTest() {

        // Sample Data
        String test1Sample = "100 5000 7000 6500 3200 10430 98240 200000 120100 789000";

        int test1 = yhJpPortalOne.calcSumAmt(test1Sample);

        logger.info("Test1 Result : {}", test1);
    }

}
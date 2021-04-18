package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.Before;
import org.junit.Test;

public class GrowthTwoCalcMulMaxValTest extends CommonExtends {

    private GrowthTwoCalcMulMaxVal growthTwoCalcMulMaxVal;

    @Before
    public void init() {
        this.growthTwoCalcMulMaxVal = new GrowthTwoCalcMulMaxVal();
    }

    @Test
    public void solutionTest() {

        int[] testSample1 = new int[]{3,1,2,5,4};
        int test1 = growthTwoCalcMulMaxVal.solution(testSample1);

        int[] testSample2 = new int[]{3,1,2,5,4,10,43};
        int test2 = growthTwoCalcMulMaxVal.solution(testSample2);

        int[] testSample3 = new int[]{3,1};
        int test3 = growthTwoCalcMulMaxVal.solution(testSample3);

        int[] testSample4 = new int[]{};
        int test4 = growthTwoCalcMulMaxVal.solution(testSample4);

        int[] testSample5 = null;
        int test5 = growthTwoCalcMulMaxVal.solution(testSample5);

        logger.info("Test1 Result : {}", test1);
        logger.info("Test2 Result : {}", test2);
        logger.info("Test3 Result : {}", test3);
        logger.info("Test4 Result : {}", test4);
        logger.info("Test5 Result : {}", test5);
    }
}
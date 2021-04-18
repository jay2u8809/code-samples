package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.Before;
import org.junit.Test;

public class GrowthOneCheckDupCharTest extends CommonExtends {

    private GrowthOneCheckDupChar growthOneCheckDupChar;

    @Before
    public void init() {
        this.growthOneCheckDupChar = new GrowthOneCheckDupChar();
    }

    @Test
    public void solutionTest() {

        // Test1
        int test1 = this.growthOneCheckDupChar.solution("foobarbaz");
        // Test2 : 重複な文字が無い場合
        int test2 = this.growthOneCheckDupChar.solution("foarbz");
        // Test3 : null場合
        int test3 = this.growthOneCheckDupChar.solution(null);
        // Test4 : 長さが「0」の場合
        int test4 = this.growthOneCheckDupChar.solution("");
        // Test5
        int test5 = this.growthOneCheckDupChar.solution("foobarbazcczfiffhdsie");

        logger.info("Test1 Result : {}", test1);
        logger.info("Test2 Result : {}", test2);
        logger.info("Test3 Result : {}", test3);
        logger.info("Test4 Result : {}", test4);
        logger.info("Test5 Result : {}", test5);
    }
}
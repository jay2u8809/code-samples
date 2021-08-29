package com.jay2u8809.codesamples.individual.algorithm.exam.yhjpportal;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class YhJpPortalTwoTest extends CommonExtends {

    private YhJpPortalTwo yhJpPortalTwo;

    @BeforeEach
    public void init() {
        this.yhJpPortalTwo = new YhJpPortalTwo();
    }

    @Test
    public void solutionTest() {

        // Sample data
        String test1Sample =    "10\n" +
                                "3\n" +
                                "type1.A.10011.001.Tokyo.Japan\n" +
                                "type1.A.10012.001.Tokyo.Japan\n" +
                                "type1.B.10012.001.Tokyo.Japan\n" +
                                "type1.A.10011.002.Tokyo.Japan\n" +
                                "type2.10001.001.Nagoya.Japan\n" +
                                "type1.A.10012.002.Tokyo.Japan\n" +
                                "type1.B.10012.002.Tokyo.Japan\n" +
                                "type2.B.10012.002.Tokyo.Japan\n" +
                                "type1.10001.001.Osaka.Japan\n" +
                                "type2.10001.001.Osaka.Japan";
        String test1 = yhJpPortalTwo.printCountNum(Arrays.asList(test1Sample.split("\n")));

        // Sample data
        String test2Sample =    "3\n" +
                                "2\n" +
                                "office01.city1.prefectureA.countryX\n" +
                                "office01.city1.prefectureA.countryY\n" +
                                "factory01.area1.city2.prefectureA.countryY\n";
        String test2 = yhJpPortalTwo.printCountNum(Arrays.asList(test2Sample.split("\n")));

        logger.info("Test1 Result : {}", test1);
        logger.info("Test2 Result : {}", test2);
    }

}
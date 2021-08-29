package com.jay2u8809.codesamples.individual.algorithm.exam.yhjpportal;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class YhJpPortalThreeTest extends CommonExtends {

    private YhJpPortalThree yhJpPortalThree;

    @BeforeEach
    public void init() {
        this.yhJpPortalThree = new YhJpPortalThree();
    }

    @Test
    public void solutionTest() {

        // Sample data
        String tes1Sample = "13 5 5\n" +
                            "900 600 700 600 2000 2300 1600 500 2800 1500 1900 2200 2500";
        String tes2Sample = "14 9 9\n" +
                            "1200 1800 800 2600 2800 1600 500 2000 1000 1700 1500 1400 1100 1400";
        String tes3Sample = "4 2 3\n" +
                            "900 2100 1900 2200";
        String tes4Sample = "4 3 3\n" +
                            "3500 1000 1800 2200";

        yhJpPortalThree.calcMinAmtUsingCoupon(Arrays.asList(tes1Sample.split("\n")));
        yhJpPortalThree.calcMinAmtUsingCoupon(Arrays.asList(tes2Sample.split("\n")));
        yhJpPortalThree.calcMinAmtUsingCoupon(Arrays.asList(tes3Sample.split("\n")));
        yhJpPortalThree.calcMinAmtUsingCoupon(Arrays.asList(tes4Sample.split("\n")));
    }

}
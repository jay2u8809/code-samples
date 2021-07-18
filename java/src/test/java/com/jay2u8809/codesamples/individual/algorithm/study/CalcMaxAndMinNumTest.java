package com.jay2u8809.codesamples.individual.algorithm.study;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Calc max num and min num of list
 */
class CalcMaxAndMinNumTest {

    private List<Integer> dummyList = new ArrayList<>(Arrays.asList(90, 30, 12, 100, 34, 98));

    @Test
    void calcMaxAndMinNumTest() {

        calcMaxNum();
        calcMinNum();
    }

    /**
     * calc max num of list
     */
    private void calcMaxNum() {

        Integer maxNum = dummyList.get(0);

        int idx = 0;
        int maxIdx = idx;
        for (Integer integer : dummyList) {
            if ( integer.compareTo(maxNum) > 0 ) {
                maxNum = integer;
                maxIdx = idx;
            }
            maxIdx++;
        }

        System.out.println("calcMaxNum - maxNum : " + maxNum + ", maxIdx : " + maxIdx);

        int maxNum2 = dummyList.stream().max(Integer::compare).orElse(maxNum);
        int maxNum3 = dummyList.stream().max((x, y) -> x - y).orElse(maxNum);
        System.out.println("calcMaxNum, Stream - maxNum : " + maxNum2 + ", maxNum : " + maxNum3);
    }

    /**
     * calc min num of list
     */
    private void calcMinNum() {

        Integer minNum = dummyList.get(0);

        int idx = 0;
        int minIdx = idx;
        for (Integer integer : dummyList) {
            if ( integer.compareTo(minNum) < 0 ) {
                minNum = integer;
                minIdx = idx;
            }
            minIdx++;
        }

        System.out.println("calcMinNum - minNum : " + minNum + ", minIdx : " + minIdx);

        int minNum2 = dummyList.stream().min(Integer::compare).orElse(minNum);
        int minNum3 = dummyList.stream().min((x, y) -> x - y).orElse(minNum);
        System.out.println("calcMaxNum, Stream - minNum : " + minNum2 + ", minNum : " + minNum3);
    }

}

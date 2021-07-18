package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumberFourTest {

    @Test
    public void test () {
        NumberFour n = new NumberFour();
        int[] testArr = {5,4,3,2,1};
//        int[] testArr = {1,2,3,4,5};
        int[] test = n.solution(testArr, 6);
        Arrays.stream(test).forEach(System.out::println);
    }
}
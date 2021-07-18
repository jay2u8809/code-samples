package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumberThreeTest {

    @Test
    public void test () {

        NumberThree n = new NumberThree();
        int[] exampleArr = {2,1,2,-1,3};
        int[] result = n.solution(3, exampleArr);
        Arrays.stream(result).forEach(System.out::println);
    }
}
package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumberTwoTest {

    @Test
    public void test() {

        NumberTwo n = new NumberTwo();
        String[] testArr = {"OFFER hello","OFFER world","OFFER !","TAKE","SIZE"};
        String[] test = n.solution(2, testArr);
        Arrays.stream(test).forEach(System.out::println);
    }
}
package com.jay2u8809.codesamples.individual.codingtests.study;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FactorialTest {

    private List<Integer> dummyList = new ArrayList<>(Arrays.asList(90, 30, 12, 100, 34, 98));

    @Test
    void factorialTest() {

        basicFactorial(-2);
        basicFactorial(0);
        basicFactorial(1);
        basicFactorial(2);
        basicFactorial(3);
        basicFactorial(4);
        basicFactorial(5);
        basicFactorial(10);

        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + -2 + " result : "+ basicFactorialUsingRecursion(-2));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 0 + " result : "+ basicFactorialUsingRecursion(0));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 1 + " result : "+ basicFactorialUsingRecursion(1));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 2 + " result : "+ basicFactorialUsingRecursion(2));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 3 + " result : "+ basicFactorialUsingRecursion(3));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 4 + " result : "+ basicFactorialUsingRecursion(4));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 5 + " result : "+ basicFactorialUsingRecursion(5));
        System.out.println("===== basicFactorialUsingRecursion ===== " + "num :  " + 10 + " result : "+ basicFactorialUsingRecursion(10));

        System.out.println("===== calcSumNumberUsingRecursion ===== " + "num :  " + 10 + " result : "+ calcSumNumberUsingRecursion(10));
        System.out.println("===== calcSumNumberUsingRecursion ===== " + "num :  " + 100 + " result : "+ calcSumNumberUsingRecursion(100));

        int maxNum = findMaxNum(dummyList);
        System.out.println("maxnum : " + maxNum);
        int minNum = findMinNum(dummyList);
        System.out.println("minNum : " + minNum);
    }

    /**
     * Basic Factorial
     * @param num
     */
    private void basicFactorial(int num) {

        int result = 1;

        if ( num < 1 ) {

            System.out.println("===== basicFactorial ===== " + "num :  " + num + " result : "+ result);
            return;
        }

        for (int i = 1; i <= num; i++) {
            result *= i;
        }

        System.out.println("===== basicFactorial ===== " + "num :  " + num + " result : "+ result);
    }

    /**
     * Basic Factorial using recursion
     * @param num
     */
    private int basicFactorialUsingRecursion(int num) {

        int result = 1;

        if ( num < 1 ) {

            return result;
        }

        result = num * basicFactorialUsingRecursion(num - 1);

        return result;
    }

    /**
     * Calc sum of number using recursion
     * @param num
     */
    private int calcSumNumberUsingRecursion(int num) {

        int result = 0;

        if ( num < 1 ) {

            return result;
        }

        result = num + calcSumNumberUsingRecursion(num - 1);

        return result;
    }

    /**
     * find max number
     * @param numList
     * @return
     */
    private int findMaxNum(List<Integer> numList) {

        int maxNum = numList.get(0);
        int listLen = numList.size();
        if ( listLen <= 1 ) {

            return maxNum;
        }

        int recursionMax = findMaxNum(numList.subList(1, listLen));
        maxNum = maxNum < recursionMax ? recursionMax : maxNum;

        return maxNum;
    }

    /**
     * find min number
     * @param numList
     * @return
     */
    private int findMinNum(List<Integer> numList) {

        int minNum = numList.get(0);
        int listLen = numList.size();
        if ( listLen <= 1 ) {

            return minNum;
        }

        int recursionMin = findMinNum(numList.subList(1, listLen));
        minNum = minNum > recursionMin ? recursionMin : minNum;

        return minNum;
    }
}

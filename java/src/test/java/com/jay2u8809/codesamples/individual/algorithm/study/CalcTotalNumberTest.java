package com.jay2u8809.codesamples.individual.algorithm.study;

import org.junit.jupiter.api.Test;

/**
 * Calc Total Number (1 ~ N)
 */
class CalcTotalNumberTest {

    @Test
    void calcTotalNumberTest() {

        calcSumTotalNumber(4, 15);
        calcSumTotalNumberUsingFormula(4, 15);

        calcSumTotalSquareNumber(1, 4);
        calcSumTotalSquareNumberUsingFormula(1, 4);

        calcSumNumberMultipleIndex(3, 5);
    }

    /**
     * Calculate sum of number without using formula
     * ex) 1 ~ 10, 3 ~ 45
     * @param startNum
     * @param endNum
     */
    private void calcSumTotalNumber(int startNum, int endNum) {

        int result = 0;
        for ( int i = startNum; i <= endNum; i++ ) {

            result += i;
        }

        System.out.println("calcSumTotalNumber ( " + startNum + " ~ " + endNum + ") :" + result);
    }

    /**
     * Calculate sum of number using formula
     * ex) 1 ~ 10, 3 ~ 45
     * formula : num * (num + 1) / 2
     * @param startNum
     * @param endNum
     */
    private void calcSumTotalNumberUsingFormula(int startNum,  int endNum) {

        int result;

        startNum--; // endNumTotalSum(0 ~ endNum) -  (startNum-1)TotalSum (0 ~ startNum - 1)

        // Subtract the sum up to startNum from the sum up to endNum
        int endSum = endNum * (endNum + 1) / 2;         // the sum up to endNum
        int startSum = startNum * (startNum + 1) / 2;   // the sum up to startNum
        result = endSum - startSum;

        System.out.println("calcSumTotalNumberUsingFormula ( " + startNum + " ~ " + endNum + ") :" + result);
    }

    /**
     * Calculate sum of squares without using formula
     * ex) 1*1 ~ 10*10, 3*3 ~ 45*45
     * @param startNum
     * @param endNum
     */
    private void calcSumTotalSquareNumber (int startNum,  int endNum) {

        int result = 0;

        for (int i = startNum; i <= endNum; i++) {

            result += Math.pow(i, 2);
        }

        System.out.println("calcSumTotalSquareNumber ( " + startNum + " ~ " + endNum + ") :" + result);
    }

    /**
     * Calculate sum of number using formula
     * ex) 1 ~ 10, 3 ~ 45
     * formula : num * (num + 1) * (2 * num + 1) / 6
     * @param startNum
     * @param endNum
     */
    private void calcSumTotalSquareNumberUsingFormula(int startNum,  int endNum) {

        int result;

        startNum--; // endNumTotalSum(0 ~ endNum) -  (startNum-1)TotalSum (0 ~ startNum - 1)
        int endSum = (endNum * (endNum + 1) * (2 * endNum + 1)) / 6;
        int startSum = (startNum * (startNum + 1) * (2 * startNum + 1)) / 6;
        result = endSum - startSum;

        System.out.println("calcSumTotalSquareNumberUsingFormula ( " + startNum + " ~ " + endNum + ") :" + result);
    }

    /**
     * Calculate the sum of the product of a number and index without using a formula
     * ex) 3 ~ 5 : 3*1 + 4*2 + 5*3
     * @param startNum
     * @param endNum
     */
    private void calcSumNumberMultipleIndex(int startNum,  int endNum) {

        int result = 0;
        int end = endNum - startNum;
        end++;
        for (int i = 1; i <= end; i++) {

            result += i*startNum;
            startNum++;
        }

        System.out.println("calcSumNumberMultipleIndex ( " + startNum + " ~ " + endNum + ") :" + result);
    }
}

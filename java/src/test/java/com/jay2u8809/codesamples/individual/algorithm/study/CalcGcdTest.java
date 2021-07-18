package com.jay2u8809.codesamples.individual.algorithm.study;

import org.junit.jupiter.api.Test;

class CalcGcdTest {

    @Test
    void calcGcdNumberTest() {

        calcGcdNumber(2, 3);
        calcGcdNumber(1, 6);
        calcGcdNumber(4, 26);
        calcGcdNumber(27, 81);

        System.out.println("euclidGcd : " + euclidGcd(2, 3));
        System.out.println("euclidGcd : " + euclidGcd(1, 6));
        System.out.println("euclidGcd : " + euclidGcd(4, 26));
        System.out.println("euclidGcd : " + euclidGcd(27, 81));

    }

    /**
     * Calc Gcd(Greatest Common Divisor) number
     * @param num1
     * @param num2
     */
    private void calcGcdNumber(int num1, int num2) {

        int result = num1 < num2 ? num1 : num2;

        while ( result > 0 ) {

            if ( num1 % result == 0 && num2 % result == 0 ) {

                System.out.println("calcGcdNumber : " + result);
                return;
            }
            result--;
        }

        System.out.println("calcGcdNumber : " + result);
    }

    /**
     * Calc gcd number using euclid
     * ex) gcd(num1, num2) == gcd(num2, num1 % num2), gcd(num1, 0) = num1
     * @param num1
     * @param num2
     * @return
     */
    private int euclidGcd(int num1, int num2) {

        if ( num2 < 1 ) {

            return num1;
        }

        int result = euclidGcd(num2, num1 % num2);

        return result;
    }
}

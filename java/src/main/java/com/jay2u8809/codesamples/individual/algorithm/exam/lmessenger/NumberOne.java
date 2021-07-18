package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

public class NumberOne {
    /**
     * 練習問題ー四角形の面積
     * 与えられた横と縦の長さで長方形の面積を求めるプログラムを作成してください。
     * Input and Output Specification
     * class Solution { public int solution(int X, int Y); }
     *
     * Input
     * 長方形の横と縦の長さが引数として
     * solution
     *  関数に与えられます。それぞれの長さは0-1024の間の整数値です。
     * Output
     * solution
     *  は長方形の面積を整数値で返します。
     *
     * Example
     * Input
     * 2, 3
     * Output
     * 6
     */


    /**
     * Solution description.
     *
     * Time Complexity: ...
     * Space Complexity: ...
     */
    public int solution(int width, int height) {
        if (width < 0 || 1024 < width) {
            // System.out.println("0~1024の間の整数");
            return -1;
        }
        if (height < 0 || 1024 < height) {
            // System.out.println("0~1024の間の整数");
            return -1;
        }
        return width * height;
    }
}

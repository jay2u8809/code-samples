package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

public class NumberThree {

    /**
     * スライディングウィンドウ(Sliding window)における最大値を求める
     *
     * Problem Statement
     * ウィンドウサイズWと数列Sが与えられ、ウィンドウの中のW個の値しか確認することができません。Sから数字を取得するたびに、スライディングウィンドウの中で最大値を計算するように実装してください。
     *
     * Input and Output Specification
     * class Solution { public int[] solution(int X, int[] B); }
     *
     * Input
     * W, [N_1, N_2, ..., N_n]
     *
     * ここで W はウィンドウサイズを表す整数、N_1, ..., N_n は数列に含まれる整数値です。
     * なお，以下の仮定を前提として問題に解答してください。
     *
     * -ウィンドウサイズを表すWは、0 < W <= 2,147,483,647の整数です。
     * -数列の各要素は、-2,147,483,648 <= N_i <= 2,147,483,647の整数です。
     *
     * Output
     * 最初のW - 1個の値を読み込んだ後、数列から値を読み込む度にスライディングウィンドウの中の最大値を計算結果の配列に加えます。
     * solution
     *  関数はスライディングウィンドウ中の最大値を全て保存した配列を返却します。
     * [V1,V2,...]
     * ...
     *
     * Input Example
     * 以下はウィンドウサイズW=2、数列S=[2, 1, 2, -1, 3]の場合の入力例です。
     * 2,[2,1,2,-1,3]
     *
     * Output Example
     * [2,2,2,3]
     */

    /**
     * Solution description.
     *
     * Time Complexity: ...
     * Space Complexity: ...
     */
    public int[] solution(int windowSize, int[] numbers) {

        int resultArrLen = numbers.length - windowSize + 1;
        int[] result = new int[resultArrLen];

        for (int i=0; i<resultArrLen; i++) {

            int maxNum = numbers[i];
            for (int j=i+1; j<i+windowSize; j++) {
                if (numbers[j] <= maxNum) continue;
                maxNum = numbers[j];
            }
            result[i] = maxNum;
        }

        return result;
    }
}

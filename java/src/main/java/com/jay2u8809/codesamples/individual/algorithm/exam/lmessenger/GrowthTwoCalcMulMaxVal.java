package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import java.util.Arrays;

/*
　　GrowthNumberTwo　　

    整数だけで構成されたリストから3つの数を選択して掛け算を行い、その積が最大になるような値を出力するプログラムを実装してください。
    入力は整数値が , 区切りで渡されます。リストの要素の数が3に満たない場合は-1を出力してください

    ※ 入力される整数には0や負の値が含まれる場合があります。

    -入力例（test-input.txtの入力値として利用）
    [3,1,2,5,4]

    -出力
    60
 */
public class GrowthTwoCalcMulMaxVal {

    public int solution(int[] A) {

        // 選択数量
        final int STD_CNT = 3;
        // Arrayの長さ
        int len = (A == null) ? 0 : A.length;
        // Arrayの長さが選択数量以下の場合
        if (len < STD_CNT)  return -1;

        // Sort Arrays
        Arrays.sort(A);

        int result = 1;
        int idx = 0;
        while (idx < STD_CNT) {
            result *= A[len-1-idx];
            idx++;
        }

        return result;
    }
}

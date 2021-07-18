package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

public class NumberFour {

    /**
     * 合計がターゲット値と一致するペアを組む
     * Problem Statement
     * バラバラな長さの複数の棒と、固定の長さのケースがあります。長さの合計がケースの長さと一致する2本の棒を選んでください。
     *
     * Input and Output Specification
     * class Solution { public int[] solution(int[] A, int Y); }
     *
     * Input
     * [L1,L2,...,Ln],target
     * 一つ目の引数 (長さ n の配列) は各棒の長さを表す整数のリストになっています。二つ目の引数は整数のターゲット値です。この数字は棒を入れるケースの長さを意味します。
     *
     * Output
     * 長さの合計がターゲット値と一致するペアの長さを返します。
     * [La, Lb]
     *
     * 戻り値のペアは昇順で値を格納します。(例：[1,2]は正、[2,1]は誤)
     * 二組以上のペアを見つけた場合は、より小さい数字を含むペアを返します。(例：ターゲット値が5で、[1,4]と[2,3]が見つかった場合、[1,4]を返するようにします)
     * 上記条件にマッチするペアが見つからない場合、空の配列[]を返します。
     *
     * Example
     * Input
     * [1,2,3,4,5],6
     *
     * Output
     * [1,5]
     */

    public int[] solution(int[] numbers, int target) {

        // sorting
        for (int i=0; i<numbers.length; i++) {

            for (int j=i+1; j < numbers.length; j++) {

                if (numbers[i] > numbers[j]) {
                    int temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }

        int[] resultArr = new int[2];
        boolean isSuccess = false;
        for (int i=0; i<numbers.length-1; i++) {

            int num1 = numbers[i];
            int num2 = 0;
            for(int j=i+1; j<numbers.length; j++) {

                int sum = num1 + numbers[j];

                if (target != sum)  continue;

                num2 = numbers[j];
                isSuccess = true;
            }

            if (!isSuccess) continue;

            resultArr[0] = num1;
            resultArr[1] = num2;
            break;
        }


        return resultArr;
    }
}

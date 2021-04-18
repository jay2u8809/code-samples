package com.jay2u8809.codesamples.individual.algorithm.exam.yhjpportal;

import java.util.Arrays;

/*
    ・説明:
        Bobは新しい服が欲しくて、10着の服を購入候補としてリストアップしました。
        Bobはいいものを欲しがるとキリがないので、10000円以下の服しか買わないというルールを自分に課しています。
        10000円以下の服をすべて購入するとき、合計の代金を求めてください。

    ・入力:
        服の価格をしめす10個の数値が標準入力から空白区切りで1行あたえられます。
        それぞれの金額は1以上100000以下の整数です。

    ・出力:
        10000円以下の服をすべて購入するとき、合計の代金を標準出力に出力してください。
 */

public class YhJpPortalOne {

    public Integer calcSumAmt(String priceStr) {

        //　基準金額
        final int STD_AMT = 10000;

        int result = 0;
        String[] priceArr = priceStr.split(" ");

        if(priceArr.length == 0) {
            return result;
        }

        result = Arrays.stream(priceArr)
                .mapToInt(Integer::valueOf)
                .filter(f -> f <= STD_AMT)
                .sum();

        return result;
    }

}

package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import java.util.HashMap;
import java.util.Map;

/*
　　GrowthNumberOne

    ある文字列が全て固有の文字で構成されているかどうかを判定せよ。
    重複する文字が存在する場合、重複している文字種の数を返却せよ、重複がない場合は0を出力せよ。

    -入力例（test-input.txtの入力値として利用）
    "foobarbaz"

    -出力
    3
 */
public class GrowthOneCheckDupChar {

    public int solution(String S) {

        // null check
        if (S == null || S.isEmpty())   return 0;
        // String -> char[]
        char[] chars = S.toCharArray();
        // 速い検索のためのMapデータ構造を使用
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {

            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 0);
/*
            // 同様な値が存在する場合
            if(map.containsKey(c)) {
                int counter = map.get(c);
                map.put(c, ++counter);
            }
            // 値が無い場合
            else {
                map.put(c, 0);
            }
*/
        }

        // 同様な文字が無い数量算出
        int result = (int) map.values().stream().filter(f -> f != 0).count();
        return result;
    }


}

package com.jay2u8809.codesamples.individual.algorithm.exam.yhjpportal;

import java.util.*;
import java.util.stream.Collectors;

/*
    ・課題名：拠点数の分析
    ・説明:
        N箇所の拠点をもつ会社が、地域ごとの拠点数を調べることにしました。
        拠点には office01.tokyo.japan といったピリオドで区切られた半角英数字の文字列の識別子が与えられています。
        この拠点は japan という地域の中の tokyo という地域の中にあることを意味します。ピリオドで区切られた右側ほど広い地域を区別するための情報になっています。
        ピリオドの個数は識別子によって異なる可能性もある点に注意してください。例えば先程の office01.tokyo.japan という3階層の識別子と factory01.area1.tokyo.japanという4階層の識別子が同時に存在することもあります。
        広い方からD階層の地域で区別して拠点の数を合計し、拠点の数が多い地域から順番に出力してください。
        D=1のときは japan 、D=2のときは tokyo.japan が集計すべき地域の単位です。Dは最大10まで想定する必要があります。
        拠点数が同じ地域は任意の順番で出力して構いません。

    ・入力:
        標準入力の最初の行と次の行にNとDが与えられ、続くN行に拠点の識別子が与えられます。
        入力には次の制約があります
        Nは1以上、50以下の整数
        Dは1以上、10以下の整数
        拠点の識別子は英数字とピリオドからなる100文字以下の文字列で互いに異なる

    ・出力:
        拠点の識別子と同じピリオド区切りの文字列でD階層の地域を表現し、空白区切りで地域と拠点数を一行にして標準出力に拠点数が多い地域から順番に出力してください。


    ・サンプル１
    　入力
        10
        3
        type1.A.10011.001.Tokyo.Japan
        type1.A.10012.001.Tokyo.Japan
        type1.B.10012.001.Tokyo.Japan
        type1.A.10011.002.Tokyo.Japan
        type2.10001.001.Nagoya.Japan
        type1.A.10012.002.Tokyo.Japan
        type1.B.10012.002.Tokyo.Japan
        type2.B.10012.002.Tokyo.Japan
        type1.10001.001.Osaka.Japan
        type2.10001.001.Osaka.Japan
    予想出力
        002.Tokyo.Japan 4
        001.Tokyo.Japan 3
        001.Osaka.Japan 2
        001.Nagoya.Japan 1

    ・サンプル２
    　入力
        3
        2
        office01.city1.prefectureA.countryX
        office01.city1.prefectureA.countryY
        factory01.area1.city2.prefectureA.countryY
    　予想出力
        prefectureA.countryY 2
        prefectureA.countryX 1

 */
public class YhJpPortalTwo {

    public String printCountNum(List<String> dataStrList) {

        int n = Integer.parseInt(dataStrList.get(0));
        int d = Integer.parseInt(dataStrList.get(1));

        if (n < 0 || 50 < n) return null;
        if (d < 0 || 10 < d) return null;

        Map<String, Integer> resultMap = new HashMap<>();

        int listLen = dataStrList.size();
        for (int i = 2; i < listLen; i++) {

            String[] strArr = dataStrList.get(i).split("\\.");

            // make temporary key
            StringJoiner tempKey = new StringJoiner(".");
            for (int j = strArr.length - d; j < strArr.length; j++) {
                tempKey.add(strArr[j]);
            }

            // make resultMap data
            String key = tempKey.toString();
            if (resultMap.containsKey(key)) {
                resultMap.put(key, resultMap.get(key) + 1);
            } else {
                resultMap.put(key, 1);
            }
        }

        List<Map.Entry<String, Integer>> entries
                = resultMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // make result value
        String result;
        StringBuilder resultBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            resultBuilder.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        result = new String(resultBuilder);

        return result;
    }
}

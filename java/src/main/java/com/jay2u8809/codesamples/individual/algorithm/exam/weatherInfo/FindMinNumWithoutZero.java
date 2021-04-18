package com.jay2u8809.codesamples.individual.algorithm.exam.weatherInfo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
  【課題】
  　◆フロー
  　　①文字列型の数字を入力
  　　②数字を最も最小値を探す

  　◆条件
  　　入力した元の桁が変更されないようにする

  　◆例
  　　入力
  　　　402
  　　出力
  　　　204
  　　　（024, 042, 204, 240, 402, 420：０から始まる数字「024, 042」は外す）
 */

public class FindMinNumWithoutZero {

    /**
     * 文字列型の数字の順番を設定する
     * @param numberStr
     */
    public String makeNumberByString(String numberStr) {

        if (numberStr == null || numberStr.isEmpty()) {
            System.err.println("Input Value is Null or Empty");
            return null;
        }

        // 文字列の数字の順番設定
        List<Integer> sortedNumbers
                = numberStr.chars()
                .mapToObj(Character::getNumericValue)
                .sorted()
                .collect(Collectors.toList());

        // 1番目の０では無い数字のindex値取得
        int sortedNumbersLen = sortedNumbers.size();
        int firstNotZeroIdx = IntStream.range(0, sortedNumbersLen)
                .filter(i -> sortedNumbers.get(i) != 0)
                .findFirst()
                .orElse(0);

        // 位置変更
        sortedNumbers.add(0, sortedNumbers.get(firstNotZeroIdx));
        sortedNumbers.remove(++firstNotZeroIdx);

        // 文字列に形式変更
        return sortedNumbers.toString().replaceAll("[^0-9]", "");
    }

}

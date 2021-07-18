package com.jay2u8809.codesamples.individual.algorithm.exam.worksapps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 【課題】
 　◆フロー
 　　①コンソールで２つの文字列を入力もらう
 　　②先に入力もらった文字列の各文字が後で入力もらった文字列にあるか無いかを判断
 　　③あれば、「True」文字列を表示、無ければ、「False」文字列を表示
 　◆条件
 　　各文字列長：1000
 　　文字列の範囲 a~z, A~Z
 　　文字列区分フラグ：スペースキー
 　◆例
 　　入力
 　　　ABCABC AABBBCC
 　　出力
 　　　True
 　　入力
 　　　ABCABCD AABBBCC
 　　出力
 　　　False
 */

public class JudgmentIncludeCharacters {

    public static final String INTRODUCTION_MESSAGE_PROGRAM_START = "文字列を入力してください。\"exit\"を入力すれば終了されます。";
    public static final String INTRODUCTION_MESSAGE_PROGRAM_END = "プログラムを終了します。";
    public static final String INTRODUCTION_MESSAGE_INPUT_VALUES = "文字列 数字 : 。";
    public static final String ERR_MESSAGE_INPUT_VALUES_COUNT = "２個の値を入力してください。";

    public JudgmentIncludeCharacters() {
    }

    public void judgmentIncludeCharactersMain () {

        while (true) {

            // 入力
            Scanner sc = new Scanner(System.in);
            System.out.println(INTRODUCTION_MESSAGE_PROGRAM_START);
            System.out.print(INTRODUCTION_MESSAGE_INPUT_VALUES);
            String inputValue = sc.nextLine();

            if (inputValue == null || inputValue.length() == 0) {
                System.out.println(ERR_MESSAGE_INPUT_VALUES_COUNT);
                continue;
            }

            // 終了
            String exitInputValue = "exit";
            if (exitInputValue.equals(inputValue.toLowerCase())) {
                System.out.println(INTRODUCTION_MESSAGE_PROGRAM_END);
                break;
            }

            // 入力値を切れて文字列と数字で区分
            String[] splitValue = inputValue.split(" ");
            if (splitValue.length != 2) {
                System.out.println(ERR_MESSAGE_INPUT_VALUES_COUNT);
                continue;
            }

            String value1 = splitValue[0];      // 入力値１
            String value2 = splitValue[1];      // 入力値２

            boolean isContain = isContainCharacters(value1, value2);

            // 結果出力
            System.out.println("Result : " + (isContain ? IsExist.True : IsExist.False));
        }
    }

    public Boolean isContainCharacters(String str1, String str2) {

        // 文字列を文字リストで変換させ、重複な文字を除去
        List<Character> str1CharList = str1.chars()
                .mapToObj(f -> (char)f)
                .collect(Collectors.toCollection(ArrayList::new));
        TreeSet<Character> treeSet = new TreeSet<>(str1CharList);
        str1CharList = new ArrayList<>(treeSet);
        System.out.println("【DEBUG】入力値１の重複値除去：" + str1CharList);

        // 入力値１が入力値２であるか判断
        List<String> resultList = str1CharList.stream()
                .map(String::valueOf)
                .filter(str2::contains)
                .collect(Collectors.toList());
        System.out.println("【DEBUG】入力値２で有無可否：" + str1CharList);

        // 2つのリストのサイズが同じならTrue
        return (resultList.size() == str1CharList.size());
    }
}

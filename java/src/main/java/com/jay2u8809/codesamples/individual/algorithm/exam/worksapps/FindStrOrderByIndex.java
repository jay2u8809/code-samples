package com.jay2u8809.codesamples.individual.algorithm.exam.worksapps;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

/**
 【課題】
 　◆フロー
 　　①コンソールで１つの文字列と数字を入力もらう
 　　②上の文字列で作られる全ての文字列のケースの順番として「数字」番目の文字列を探す
 　◆条件
 　　文字列長 < 1000
 　　文字列の範囲 a~z, A~Z
 　　数字 < 10000000000000
 　　文字列区分フラグ：スペースキー
 　◆例
 　　入力
 　　　ABC 3
 　　出力
 　　　BAC　　（ABC, ACB, BAC, BCA, CAB, CBA）
 */
public class FindStrOrderByIndex {

    // 入力いただいた文字列によってケース
    private static char[] words;

    // 入力いただいた文字列によって全てのケースリスト
    private static List<String> wordsList;

    public void findStrOrderByIndexMain() {
        while (true) {

            // 入力
            Scanner sc = new Scanner(System.in);
            System.out.println("文字列を入力してください。\"exit\"を入力すれば終了されます。");
            System.out.print("文字列 数字 : ");
            String inputValue = sc.nextLine();

            if (inputValue == null || inputValue.length() == 0) {
                System.out.println("２個の値を入力してください。");
                continue;
            }

            // 終了
            String exitInputValue = "exit";
            if (exitInputValue.equals(inputValue.toLowerCase())) {
                break;
            }

            String value;       // 入力値１
            int findIndex;      // 探している文字列の順番

            try {

                // 入力値を切れて文字列と数字で区分
                String[] splitValue = inputValue.split(" ");
                if (splitValue.length != 2) {
                    System.out.println("２個の値を入力してください。");
                    continue;
                }

                value = splitValue[0];
                findIndex = Integer.parseInt(splitValue[1]);

            } catch (NumberFormatException e) { // 数字ではない場合、再び入力いただく

                System.out.println("2つ目の値はぜひ数字で入力してください。");
                continue;
            }

            // 入力いただいた文字列の各文字の順番変更
            String resultWord = wordCharacterSort(value);

            char[] resultWordArr = resultWord.toCharArray();
            int resultWordArrLen = resultWordArr.length;
            words = new char[resultWordArrLen];

            // 全てのケースを文字列を生成、リストに保存
            wordsList = new ArrayList<>();
            generateWord(resultWordArrLen, 0, getRemainCharArr(resultWordArr, -1));

            // 重複値除去
            TreeSet<String> treeSet = new TreeSet<>(wordsList);
            wordsList = new ArrayList<>(treeSet);

            System.out.println("Final Result : " + wordsList.get(findIndex-1));
        }
    }

    /**
     * 入力いただいた値をローマ字順番で設定
     * @param answer
     * @return
     */
    private static String wordCharacterSort(String answer) {

        if ( answer == null || answer.length() == 0 ) {
            return null;
        }

        char[] answerCharArr = answer.toCharArray();

        // 文字の順番変更
        for (int i = 0; i < answerCharArr.length - 1; i++) {

            for (int j = i + 1; j < answerCharArr.length; j++) {

                if ( answerCharArr[i] > answerCharArr[j] ) {

                    char tempChar = answerCharArr[i];
                    answerCharArr[i] = answerCharArr[j];
                    answerCharArr[j] = tempChar;
                }
            }
        }

        // 文字列型で変換
        return new String(answerCharArr);
    }

    /**
     * 文字列の全てのケース生成
     * @param n
     * @param depth
     * @param remainWords
     */
    private static void generateWord(int n, int depth, char[] remainWords){

        //各ケース別に文字列を生成
        for(int i = 0; i < n; i++){

            words[depth] = remainWords[i];

            if(remainWords.length == 1){
//                System.out.println(new String(words));
                wordsList.add(new String(words));
            }

            generateWord(n - 1, depth + 1, getRemainCharArr(remainWords, i));
        }
    }

    /**
     * 文字列の文字づつにケースを生成
     * @param word
     * @param removeIdx
     * @return
     */
    private static char[] getRemainCharArr(char[] word, int removeIdx){

        // 最初の場合
        if(removeIdx == -1){
            return word;
        }

        char[] result = new char[word.length - 1];

        int index = 0;
        for(int i = 0; i < word.length; i++){

            if(i != removeIdx){

                result[index] = word[i];
                index++;
            }
        }

        return result;

    }
}

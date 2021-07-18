package com.jay2u8809.codesamples.individual.algorithm.exam.ninten;

import java.util.List;

/*

    問題A
    --------
    ある日、ニンテン国は大変な危機にありました。おそろしい敵が国に攻めてきたのです。
    あなたは国の勇者として、敵を倒し国を守らなければいけません。
    敵はグループになって、次々と攻めてきます。
    勇者のあなたは5人以下のグループならば倒せますが、それを超える人数には負けてしまいます。
    しかし、あなたには必殺技があります！！
    必殺技を使えば、人数に関係なく1グループを全滅させることができますが、必殺技使用可能回数を1回消費します。
    はたして、あなたは敵をすべて倒し、国を守ることが出来るでしょうか。
    そして、何人の敵を倒すことが出来るでしょうか。

    ・入力の先頭行にはセット数。
    ・各入力データセットの最初の行には必殺技の使用可能回数、次の行にはスペース区切りで敵グループとその人数。
    ・グループは行の左から順に攻めてき、勇者はその順番通り、常に1つのグループと対峙します。
    ・勇者は、5人を超える敵グループの時に必殺技を使おうとします。
    ・出力には、勝ちの場合はTrue、負けの場合はFalse、続けて、そのセットで倒した敵の数。


    サンプル入力

    2
    3
    3 2 4 6 2 4 1 5 3 5
    5
    7 4 3 4 6 4 12 3 9 9 2 4 3 7 9 10 2 1 4 12

    サンプル出力

    True 35
    False 70

 */
public class Solution {

    private static final Integer SPECIAL_SKILL_MONSTER_CNT = 5;

    public String solutionA(int specialSkillCnt, List<Integer> monsterCntList) {

        int killMonsterCnt = 0;
        if (monsterCntList == null || monsterCntList.isEmpty()) {
            return "False " + killMonsterCnt;
        }

        boolean isSuccess = true;



        long startTime = System.nanoTime();
        int len = monsterCntList.size();
        for (int i = 0; i < len ; i++) {
            int monsterCnt = monsterCntList.get(i);
//            if (SPECIAL_SKILL_MONSTER_CNT <= monsterCnt) {
//                specialSkillCnt--;
//            }
            specialSkillCnt = (SPECIAL_SKILL_MONSTER_CNT <= monsterCnt) ? --specialSkillCnt : specialSkillCnt;
            if (specialSkillCnt < 0) {
                isSuccess = len-1 <= i;     // 10239, 26384  12422, 11333
                break;
            }
            killMonsterCnt += monsterCnt;
        }
        long endTime = System.nanoTime();


/*
        long startTime = System.nanoTime();
//        int sum = monsterCntList.stream().mapToInt(Integer::intValue).sum();
        int idx = 0;
        int len = monsterCntList.size();
        for (Integer monsterCnt : monsterCntList) {

            specialSkillCnt = (SPECIAL_SKILL_MONSTER_CNT <= monsterCnt) ? --specialSkillCnt : specialSkillCnt;
            if (specialSkillCnt < 0)  {
                isSuccess = len - 1 <= idx;
                break;
            }

            killMonsterCnt += monsterCnt;
            idx++;
        }

//        isSuccess = (sum == killMonsterCnt);        // 3927209, 86998  2555076, 66686
        long endTime = System.nanoTime();
*/




        System.out.println("TIME : " + ((double) endTime - startTime));
        String result = isSuccess ? "True " : "False ";
        result += killMonsterCnt;
        return result;
//        StringBuilder builder = new StringBuilder(isSuccess ? "True " : "False ");
//        builder.append(killMonsterCnt);
//        return builder.toString();
    }


//    public String solutionB(int guessCnt, String question, int bullsCtn, int cowsCnt) {
//
//        int questionLen = question.length();
//        int cntSum = bullsCtn + cowsCnt;
//        if (cowsCnt == 0 && cntSum == questionLen) {
//            return question;
//        } else if (bullsCtn == 0 && cntSum == questionLen) {
//            return "NONE";
//        }
//
//        char[] result = question.toCharArray();
//        for (int i = 0; i < guessCnt; i++) {
//
//            char[] questionArr = result;
//
//
//
//
//
//        }
//
//
//
//
//        int []secDigitCount = new int[10];
//        int []guessDigitCount = new int[10];
//
//        int len = question.length();
//        for (int i = 0; i < len; i++){
//
//
//
//            char guessChar = guess.charAt(i);
//            Character.getNumericValue(secret.charAt(i));
//
//            if (secretChar == guessChar){
//                // Incrementing bull count
//                bullCount += 1;
//            }
//            else {
//                // Incrementing digit counts
//                secDigitCount[secretChar - '0'] += 1;
//                guessDigitCount[guessChar - '0'] += 1;
//            }
//        }
//
//        // If statements and loops to handle remaining
//        // characters in case of string length differences
//        if (secret.length() > guess.length()){
//            for (int i = guess.length(); i < secret.length(); i++){
//                secDigitCount[secret.charAt(i) - '0'] += 1;
//            }
//        }
//        else if (secret.length() < guess.length()){
//            for (int i = secret.length(); i < guess.length(); i++){
//                guessDigitCount[guess.charAt(i) - '0'] += 1;
//            }
//        }
//        // Loop to count Cows
//        for (int i = 0; i < 10; i++){
//            cowCount += Math.min(secDigitCount[i], guessDigitCount[i]);
//        }
//
//        return bullCount + " Bulls, " + cowCount + " Cows";
//
//    }
}

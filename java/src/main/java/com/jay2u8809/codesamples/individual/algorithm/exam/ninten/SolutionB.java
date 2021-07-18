package com.jay2u8809.codesamples.individual.algorithm.exam.ninten;

/*

    問題B
    --------
    Bulls and Cows という遊びがあります。

    まず、出題者が正解の4桁の数を思い浮かべます。重複する数字はなく、先頭に0がきてもかまいません。
    たとえば、0342 を正解としたとしましょう。

    次に、挑戦者がその正解を当てるために質問をします。
    そのときできる質問は、正解と同じく数字が重複しない4桁の数です。
    たとえば、0123 と質問したとします。

    それを聞いた出題者は、正解と質問を比べて、値も場所も当たっている数字の数を bulls、
    場所は違うけれども数字は当たっている数を cows として回答します。
    正解が 0342 で質問が 0123 の場合、場所も値も当たっているのは 0 のみ、
    数字だけが当たっているのが 3 と 2 ですので、回答は 1bull 2cows です。

    このようにして挑戦者が質問を繰り返し、正解を当てる、というゲームです。

    今、あるゲームでの挑戦者の質問とそれに対する回答の一覧が与えられました。
    この一覧から、そのゲームでの正解を求めてください。

    入力データは、1行目に問題セット数 N (1 <= N <= 100) が与えられ、
    続いて問題セットが N 回繰り返されます。
    各問題セットは、最初の行に質問数 K (1 <= K <= 10) があり、
    続く K 行に以下のフォーマットで質問と回答が記述されます。

    <Question> <Bulls> <Cows>

    <Question> は4桁の数字で、同じ数字が複数回出てくることはなく、0で始まる場合もあります。
    <Bulls> と <Cows> は <Question> に対する回答で、それぞれ bulls と cows の数です。
    各項目は1つのスペースで区切られています。

    出力データには、問題セットごとに正解の4桁の数字を出力し、改行してください。
    解が1つに定まらない、もしくは解の候補がない場合は
    解の代わりに None という文字列を表示してください。

    サンプル入力
    3
    1
    1234 4 0
    1
    1234 0 4
    5
    1234 0 3
    3456 0 2
    7812 2 0
    7834 0 1
    4758 1 1

    サンプル出力
    1234
    None
    4512

    https://tonks.tistory.com/115

 */
public class SolutionB {
    public static String hint(String secret, String guess){
        // Initializing count variables
        int bullCount = 0;
        int cowCount = 0;

        // Initializing arrays
        // Each array contains the count for each digit
        // at its corresponding index
        int []secDigitCount = new int[10];
        int []guessDigitCount = new int[10];
        for (int i = 0; i < Math.min(secret.length(), guess.length()); i++){
            // Getting characters
            char secretChar = secret.charAt(i);
            char guessChar = guess.charAt(i);
            Character.getNumericValue(secret.charAt(i));

            if (secretChar == guessChar){
                // Incrementing bull count
                bullCount += 1;
            }
            else {
                // Incrementing digit counts
                secDigitCount[secretChar - '0'] += 1;
                guessDigitCount[guessChar - '0'] += 1;
            }
        }

        // If statements and loops to handle remaining
        // characters in case of string length differences
        if (secret.length() > guess.length()){
            for (int i = guess.length(); i < secret.length(); i++){
                secDigitCount[secret.charAt(i) - '0'] += 1;
            }
        }
        else if (secret.length() < guess.length()){
            for (int i = secret.length(); i < guess.length(); i++){
                guessDigitCount[guess.charAt(i) - '0'] += 1;
            }
        }
        // Loop to count Cows
        for (int i = 0; i < 10; i++){
            cowCount += Math.min(secDigitCount[i], guessDigitCount[i]);
        }

        return bullCount + " Bulls, " + cowCount + " Cows";
    }

}
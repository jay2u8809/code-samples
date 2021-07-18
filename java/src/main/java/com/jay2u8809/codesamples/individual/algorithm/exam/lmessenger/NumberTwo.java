package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

public class NumberTwo {

    /**
     * サイズ制限のあるFIFOキューを実装する
     *
     * Problem Statement
     *
     * サイズ制限のあるFIFOキューを実装してください。実装したキューは下記コマンドに対応する必要があります。
     *
     * SIZE - キューに現在格納されている要素の個数を返す。キューは変更しない。
     *
     * OFFER xxx - 与えられた要素 xxx をキューに追加する。キューへの追加が受理されれば trueを返し、キューの要素数が既にサイズ制限に到達していればfalseを返す。
     *
     * TAKE- キューから要素を取得する。キューが空でなければ取得した要素を返す。キューが空の場合は何も返さない。
     *
     * Input and Output Specification
     *      class Solution { public String[] solution(int X, String[] B); }
     *
     * Input
     * C,["command 1","command 2","command N"]
     *
     * Cはキューの容量 (0 <= C <= 10000)、 Nはコマンドの数(1 <= N <= 10000)を返します。
     *
     * Output
     * solution関数は、コマンドが返した結果の文字列を実行順に格納した配列を返します。
     *
     * Input Example
     * 2,[ "OFFER hello","OFFER world","OFFER !","TAKE","SIZE"]
     *
     * Output Example
     * 上記入力に対して、下記のように出力します。
     * ["true","true","false","hello","1"]
     */

    /**
     * Solution description.
     *
     * Time Complexity: ...
     * Space Complexity: ...
     */

    private String[] queue;
    private int initCapacity;

    public String[] solution(int capacity, String[] commands) {
        initCapacity = capacity;
        queue = new String[capacity];

        String[] result = new String[commands.length];
        for (int i=0; i<commands.length; i++) {

            String[] param = commands[i].split(" ");
            switch (param[0].toLowerCase()) {
                case "offer" :
                    result[i] = offer(param[1]) ? "true" : "false";
                    break;
                case "take" :
                    result[i] = take();
                    break;
                case "size" :
                    result[i] = String.valueOf(size());
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private String take () {

        int currentSize = size();
        String result = null;

        if (currentSize == 0)   return result;

        result = this.queue[0];

        String[] tempQueue = new String[currentSize];
        for (int i=1; i<currentSize; i++) {
            tempQueue[i-1] = this.queue[i];
        }
        this.queue = tempQueue;

        return result;
    }

    private boolean offer (String s) {

        boolean isAdded = false;
        for (int i=0; i<this.queue.length; i++) {
            if (this.queue[i] != null) continue;

            this.queue[i] = s;
            isAdded = true;
            break;
        }

        return isAdded;
     }

    private int size () {
        int size = 0;
        for (String s : this.queue) {
            if (s == null) continue;
            size++;
        }
        return size;
    }

}

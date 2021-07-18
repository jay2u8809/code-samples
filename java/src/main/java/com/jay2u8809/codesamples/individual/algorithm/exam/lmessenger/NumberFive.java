package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

public class NumberFive {

    /**
     * 有向グラフがサイクルを含むか否かを答える
     * Problem Statement
     * 与えられた有向グラフ(directed graph)がサイクルを含むかどうかを答えてください。
     *
     * Input and Output Specification
     * class Solution { public boolean solution(int X, int[] B); }
     *
     * Input
     * ノードの数と辺のリストが solution関数に2つの引数として渡されます。
     * 1つ目の引数 size はグラフ上のノードの数です。
     * 2つ目の引数 edges は整数の配列で、有向グラフにおける辺のリストを表します。配列は空である可能性があります（辺がないことを示す）。 配列は空でない場合、偶数個の要素を持ちます。 各辺はノードの識別子のペアとして表現されます。
     * 例として、以下の入力
     * 4,[0,1,1,2,2,3,3,0]
     * は4つのノードと4つの辺
     * [0->1], [1->2], [2->3], [3->0]
     * からなる以下のグラフを表します。
     *
     *   0 -----> 1
     *   ↑        |
     *   |        |
     *   |        |
     *   |        ↓
     *   3 <----- 2
     *
     * このグラフはサイクル 0 -> 1 -> 2 -> 3 -> 0 を持ちます。
     *
     * Output
     * 関数 solution は、グラフがサイクルを含む場合はtrueを、そうでない場合はfalseを返します。
     *
     * Example
     * Input
     * 3,[0,1,1,2,2,0]
     *
     * Output
     * true
     */

    public Boolean solution(int size, int[] edges) {

        boolean result = false;

        if (size <= 0) return result;
        if (edges.length == 0) return result;
        if (edges[0] != edges[edges.length-1]) return result;

        // sorting
        for (int i=0; i<edges.length; i++) {

            for (int j=i+1; j < edges.length; j++) {

                if (edges[i] > edges[j]) {
                    int temp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = temp;
                }
            }
        }

        int[] newArr = new int[size];
        newArr[0] = edges[0];
        for (int i=1; i< edges.length; i++) {

            for (int j=0 ; j< newArr.length; j++) {
                if (newArr[j] == edges[i]) continue;


            }

        }

        return false;
    }
}

package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import java.util.*;

/*
　　GrowthNumberThree

    次のようなOperationを使用できるdata structureを実装してください。

    //assume K provides good hashCode()
    void add(K key, V value);　// data structureに、key、valueからなる要素を追加する。keyに対応する値が既に存在する場合は新しい値で上書きされる。
    V get(K key);　// keyに該当する要素を探してそのvalueを返す。要素がない場合Exception
    V remove(K key);　// keyに該当する要素を探してそのvalueを返し、要素を削除する。要素がない場合Exception
    void evict();　// 全要素のうちaddやgetされてからの経過時間が最も長い要素を削除

    この問題ではK, Vを2^30以下の自然数と仮定してもいいです。

    入力
    入力の各行はデーターを操作するメソッドを示します。各メソッドにパラメーターがある場合は後ろに空白文字1つで区切られたパラメーターが与えられます。(定義されていないメソッドが入力された場合は無視して次の行を処理します。)

    evict :  evict operationを実行します。
    add : add operationを実行します。パラメーターの１個目はkey、２個目はvalueを示します。
    get : get operationを実行します。パラメーターの１個目はkeyです。その実行の値を出力します。結果がないか例外が発生した場合は'-1'を出力します。
    remove :  remove operationを実行します。パラメーターの１個目はkeyです。その実行の値を出力します。結果がないか例外が発生した場合は'-1'を出力します。
    exit : 入力の処理を終了します。

    -入力例（test-input.txtの入力値として利用）
    ["add 5 3","add 1 2","get 5","evict","get 1","remove 5","exit"]

    -出力
    [3,-1,3]
 */
public class GrowthThreeMapImpl {

    // 結果データ
    private Map<Integer, Integer> mapResult;
    // evict用の臨時データ
    private Map<Integer, Integer> mapBufferData;
    // 結果を表示するためなデータ
    private int[] result = {};

    public int[] solution(String[] A) {

        if (A == null || A.length == 0)  return this.result;

        List<Integer> resultArr = new ArrayList<>();
        // 順番があるデータ構造
        this.mapBufferData = new LinkedHashMap<>();
        boolean isContinue = true;

        for (String s : A) {
            String[] param = s.split(" ");
            switch (param[0]) {
                case "add" :
                    this.add(param[1], param[2]);
                    break;
                case "get" :
                    resultArr.add(this.get(param[1]));
                    break;
                case "remove" :
                    resultArr.add(this.remove(param[1]));
                    break;
                case "evict" :
                    this.evict();
                    break;
                case "exit" :
                default:
                    isContinue = false;
                    break;
            }

            // exit()が呼び出したら、forLoopを止める
            if(!isContinue) {
                break;
            }
        }

        int resultArrLen = resultArr.size();
        if(!resultArr.isEmpty()) {
            this.result = new int[resultArrLen];
            for (int i = 0; i < resultArrLen; i++) {
                this.result[i] = resultArr.get(i);
            }
        }

        return this.result;
    }

    /**
     * mapにデータ追加
     * @param keyVal
     * @param valueVal
     */
    private void add(String keyVal, String valueVal) {

        Integer key;
        Integer value;

        // 数字ではない値はダメ
        try {

            key = Integer.valueOf(keyVal);
            value = Integer.valueOf(valueVal);

        } catch (NumberFormatException e) {

            System.err.println("It is not number");
            return;
        }

        if(this.mapResult == null) {
            this.mapResult = new LinkedHashMap<>();
        }

        this.mapResult.put(key, value);
        this.mapBufferData.put(key, value);
    }

    /**
     * キーでmapの値を返還
     * @param keyVal
     * @return
     */
    private Integer get(String keyVal) {

        Integer key;
        Integer result = -1;

        if(this.mapResult == null || keyVal == null || keyVal.length() == 0) {
            return result;
        }

        try {
            key = Integer.valueOf(keyVal);
        } catch (NumberFormatException e) {
            System.err.println("It is not number");
            return result;
        }

        if(this.mapResult.get(key) == null) {
            return result;
        }

        result = this.mapResult.get(key);
        this.mapBufferData.remove(key);

        return result;
    }

    /**
     * キーでmapの値を削除
     * @param keyVal
     * @return
     */
    private Integer remove(String keyVal) {

        Integer key;
        Integer result = -1;

        if(this.mapResult == null || keyVal == null || keyVal.length() == 0) {
            return result;
        }

        try {
            key = Integer.valueOf(keyVal);
        } catch (NumberFormatException e) {
            System.err.println("It is not number");
            return result;
        }

        result = this.mapResult.remove(key);    // 削除した後、削除されたキーの値を返還
        result = (result == null) ? -1 : result;

        return result;
    }

    /**
     * 臨時データの一番目のデータ削除
     */
    private void evict() {
        Integer firstKey = this.mapBufferData.entrySet().stream().findFirst().get().getKey();
        this.remove(String.valueOf(firstKey));
    }

    /**
     * アプリ終了
     * 使用しない
     */
    private void exit() {

    }
}

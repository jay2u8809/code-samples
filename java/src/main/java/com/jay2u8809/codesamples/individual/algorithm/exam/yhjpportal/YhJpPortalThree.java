package com.jay2u8809.codesamples.individual.algorithm.exam.yhjpportal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
     課題名：クーポン
     ・説明:
         AliceはアウトレットモールでN個の商品を買いたいと考えています。
         手元にはクーポンAがX枚、クーポンBがY枚あります。
         クーポンAは商品を5%引きにすることができます。（商品の価格は必ず100の倍数なので割引後の価格は整数です）
         クーポンBは商品が1000円以上の場合に100円引きにすることができます。
         1つの商品を買うのにクーポンAとクーポンBを同時に使うことはできません。
         どちらのクーポンもすべての店舗で有効で、クーポンを使って1個の商品を購入するごとに1枚消費されます。
         クーポンをうまく使って購入金額の合計を最小化してください。

     ・入力:
        標準入力の1行目に空白区切りで N X Y があたえられ、
        2行目に各商品の価格を示すN個の数値が空白区切りであたえられます。
        また入力は次の制約を満たします。

        1 <= N <= 20
        1 <= X, Y <= 20
        商品の価格は100以上10000以下でかつ100の倍数

      ・出力:
        クーポンをうまく使って購入金額の合計を最小にしたとき、その合計金額を標準出力に出力してください。
        出力は概ね10秒以内に得られるようにしてください。処理時間が長すぎる場合は得点が得られません。

     ・テスト１
        テスト入力
            13 5 5
            900 600 700 600 2000 2300 1600 500 2800 1500 1900 2200 2500
        予想出力
            19140
     ・テスト２
        テスト入力
            14 9 9
            1200 1800 800 2600 2800 1600 500 2000 1000 1700 1500 1400 1100 1400
        予想出力
            20065
     ・テスト３
        テスト入力
            4 2 3
            900 2100 1900 2200
        予想出力
            6745
     ・テスト４
        テスト入力
            4 3 3
            3500 1000 1800 2200
        予想出力
            8015
 */
public class YhJpPortalThree {

    public void calcMinAmtUsingCoupon(List<String> dataStrList) {

        // クーポンAの割引率
        final double SALE_RATE = 0.05;
        // クーポンBの基準金額
        final int SALE_STD_PRICE = 1000;
        // クーポンBの割引金額
        final int SALE_PRICE = 100;

        String[] dataStrArr = dataStrList.get(0).split(" ");
        int nProdCnt = Integer.parseInt(dataStrArr[0]); // 購入する商品数
        int aCouponCnt = Integer.parseInt(dataStrArr[1]); // クーポンAの数量
        int bCouponCnt = Integer.parseInt(dataStrArr[2]); // クーポンBの数量

        if (nProdCnt < 0 || 20 < nProdCnt) return;
        if (aCouponCnt < 0 || 20 < aCouponCnt) return;
        if (bCouponCnt < 0 || 20 < bCouponCnt) return;

        String[] priceStrArr = dataStrList.get(1).split(" ");
        // 商品金額情報を安い金額でソートする
        List<Integer> priceListSorted = Arrays.stream(priceStrArr)
                                    .map(Integer::valueOf)
                                    .sorted()
                                    .collect(Collectors.toList());

        // クーポンAの割引金額とクーポンBの割引金額が同じになる商品の金額：この金額以上の商品はクーポンAが良い
        int minPriceByUsingCouponA = (int) (SALE_PRICE / SALE_RATE);

        // 最終の結果金額
        Integer result;

        // クーポンBは1000円以上から2000円まで使用することが最も良い
        int prodCnt = Math.min(priceListSorted.size(), nProdCnt);
        // 購入する商品数通りのリスト生成
        List<Integer> priceList = priceListSorted.subList(0, prodCnt);

        // 割引適用される前の商品合計
        int originalTotalSum = priceList.stream().mapToInt(Integer::valueOf).sum();
        // クーポンBが最も割引率が高い商品金額帯の商品数量
        int usedBCouponProdCnt = (int) priceList.stream()
                .filter(f -> SALE_STD_PRICE <= f)
                .filter(f -> f <= minPriceByUsingCouponA)
                .count();

        // クーポンB数量更新
        bCouponCnt -= usedBCouponProdCnt;
        // クーポンBが残った場合
        if (bCouponCnt > 0) {
            // もっと使用するクーポンBの数量計算：購入する商品数量-クーポンBを使用する商品数量-クーポンA数量
            int usingBCouponCnt = prodCnt - usedBCouponProdCnt - aCouponCnt;
            // クーポンAを全部使用した後、クーポンBを使用する数量計算
            if (usingBCouponCnt > 0) {
                if (bCouponCnt >= usingBCouponCnt) {
                    // クーポンB数量更新
                    bCouponCnt -= usingBCouponCnt;
                    // 最終的なクーポンB使用数量
                    usedBCouponProdCnt += usingBCouponCnt;
                } else {
                    // 全てのクーポンB使用
                    usedBCouponProdCnt += bCouponCnt;
                    bCouponCnt = 0;
                }
            }
        }
        // 元の商品合計でクーポンBの割引額を外す
        originalTotalSum -= SALE_PRICE * usedBCouponProdCnt;

        // 割引適用基準金額以下の商品数量
        int underSaleStdPriceCnt = (int) priceList.stream()
                            .filter(f -> f < SALE_STD_PRICE)
                            .count();
        // クーポンBを使わなかった全ての商品に対してクーポンAを適用
        for (int i = prodCnt-1; i >= 0; i--) {

            // クーポンAが無い場合
            if (aCouponCnt == 0) {
                break;
            }

            Integer price = priceListSorted.get(i);
            // クーポンBを使った金額帯
            if (SALE_STD_PRICE <= price && price <= minPriceByUsingCouponA) {
                continue;
            }
            // クーポンBを使った金額帯及びIndex
            if (minPriceByUsingCouponA < price && i < underSaleStdPriceCnt + usedBCouponProdCnt) {
                continue;
            }
            int saleAmt = (int) (price * SALE_RATE);    // クーポンAを通じて割引される金額
            originalTotalSum -= saleAmt;
            aCouponCnt--;
        }

        result = originalTotalSum;
        System.out.println(result);
    }
}

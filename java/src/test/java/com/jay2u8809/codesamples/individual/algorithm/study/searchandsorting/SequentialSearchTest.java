package com.jay2u8809.codesamples.individual.algorithm.study.searchandsorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SequentialSearchTest {

    private List<Integer> dummyList
            = new ArrayList<>(Arrays.asList(
                12, 43, 546, 65, -43, 43, -78, 65, 100
            ));
    private List<Integer> carNumList
            = new ArrayList<>(Arrays.asList(
                5993, 2032, 1020, 4300, 3201
            ));
    private List<String> carNameList
            = new ArrayList<>(Arrays.asList(
                "Ferrari", "Benz", "BMW", "RangeRover", "Audi"
            ));

    @Test
    void sequentialSearchTest() {

        System.out.println("sequentialSearchFirstIndex : " + sequentialSearchFirstIndex(dummyList, 12));
        System.out.println("sequentialSearchFirstIndex : " + sequentialSearchFirstIndex(dummyList, -78));
        System.out.println("sequentialSearchFirstIndex : " + sequentialSearchFirstIndex(dummyList, 43));
        System.out.println("sequentialSearchFirstIndex : " + sequentialSearchFirstIndex(dummyList, 65));
        System.out.println("sequentialSearchFirstIndex : " + sequentialSearchFirstIndex(dummyList, 101));

        System.out.println("sequentialSearchAllIndex : " + sequentialSearchAllIndex(dummyList, 12));
        System.out.println("sequentialSearchAllIndex : " + sequentialSearchAllIndex(dummyList, -78));
        System.out.println("sequentialSearchAllIndex : " + sequentialSearchAllIndex(dummyList, 43));
        System.out.println("sequentialSearchAllIndex : " + sequentialSearchAllIndex(dummyList, 65));
        System.out.println("sequentialSearchAllIndex : " + sequentialSearchAllIndex(dummyList, 101));

        System.out.println("searchCarName : " + searchCarName(1020));
        System.out.println("searchCarName : " + searchCarName(3201));
        System.out.println("searchCarName : " + searchCarName(2032));
        System.out.println("searchCarName : " + searchCarName(1111));

    }

    /**
     *
     * @param numList
     * @param searchNum
     * @return
     */
    private int sequentialSearchFirstIndex(List<Integer> numList, int searchNum) {

        int result = -1;

        if ( numList == null || numList.size() == 0 ) {

            return result;
        }

        int numListLen = numList.size();
        for (int i = 0; i < numListLen; i++) {

            if ( numList.get(i).equals(searchNum) ) {

                result = i;
                break;
            }
        }

        return result;
    }

    /**
     *
     * @param numList
     * @param searchNum
     * @return
     */
    private List<Integer> sequentialSearchAllIndex(List<Integer> numList, int searchNum) {

        List<Integer> result = new ArrayList<>();

        if ( numList == null || numList.size() == 0 ) {

            return result;
        }

        int numListLen = numList.size();
        for (int i = 0; i < numListLen; i++) {

            if ( numList.get(i).equals(searchNum) ) {

                result.add(i);
            }
        }

        return result;
    }

    /**
     * Find car name by car number
     *
     */
    private List<String> searchCarName(int carNum) {

        List<String> result = new ArrayList<>();

        int carNumListLen = carNumList.size();
        for (int i = 0; i < carNumListLen; i++) {

            if ( carNumList.get(i).equals(carNum) ) {

                result.add(carNameList.get(i));
                return result;
            }
        }

        result.add("?");
        return result;
    }

}

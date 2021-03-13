package com.jay2u8809.codesamples.individual.codingtests.study.searchandsorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InsertionSortTest {

    private List<Integer> dummyList
            = new ArrayList<>(Arrays.asList(
                3, 4, 4, 5, 2
            ));

    @Test
    void insertionSortTest() {

        System.out.println("insertionSort : " + insertionSort(dummyList));
    }

    /**
     * find insertion index
     * @param dataList
     * @param num
     * @return
     */
    private int findInsertIndex(List<Integer> dataList, int num) {

        int result = 0;

        if ( dataList == null || dataList.size() == 0 ) {

            return result;
        }

        int listLen = result = dataList.size();
        for (int i = 0; i < listLen; i++) {

            if ( num < dataList.get(i)) {

                result = i;
                return result;
            }
        }

        return result;
    }

    /**
     * Insertion Sort
     * @param dataList
     * @return
     */
    private List<Integer> insertionSort(List<Integer> dataList) {

        List<Integer> result = new ArrayList<>();

        if ( dataList == null || dataList.size() == 0 ) {

            return result;
        }

        int dataListLen = dataList.size();
        for (Integer integer : dataList) {
            int findIdx = findInsertIndex(result, integer);
            result.add(findIdx, integer);
        }

        return result;
    }
}

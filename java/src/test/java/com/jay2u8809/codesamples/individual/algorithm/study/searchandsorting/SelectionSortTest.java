package com.jay2u8809.codesamples.individual.algorithm.study.searchandsorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SelectionSortTest {

    private List<Integer> dummyList
            = new ArrayList<>(Arrays.asList(
                2, 4, 5, 1, 3
            ));

    @Test
    void selectionSortTest() {

        System.out.println("selectionSort : " + selectionSort(dummyList));

    }

    /**
     * change list value
     * @param numList
     * @return
     */
    private List<Integer> selectionSort(List<Integer> numList) {

        int numListLen = numList.size();

        for (int i = 0; i < numListLen - 1; i++) {

            int minIdx = i;
            for ( int j = i + 1; j < numListLen; j++) {

                // Search for smaller numbers to the end of the list
                if ( numList.get(j) < numList.get(minIdx) ) {
//                if ( numList.get(j) > numList.get(minIdx) ) {   // Sort Descending
                    minIdx = j;
                }
            }
            Collections.swap(numList, i, minIdx);
        }

        return numList;
    }
}

package com.jay2u8809.codesamples.individual.codingtests.study;

import org.junit.jupiter.api.Test;

import java.util.*;

class FindSameNameTest {

    private List<String> dummyNames
            = new ArrayList<>(Arrays.asList("Ian", "Bran", "Nick", "Suzuki", "Lee", "Bran", "Suzuki"));

    @Test
    void findSameName() {

        findSameName(dummyNames);
        makeDeduplicatedList(dummyNames);
    }

    /**
     * Find same name in list
     * @param list
     */
    private void findSameName(List<String> list) {

        if ( list == null || list.size() == 0 ) {
            return;
        }

        Set<String> result = new HashSet<>();
        int listLen = list.size();
        for (int i = 0; i < listLen - 1; i++) {    // index : 0 ~ (len - 2)

            for (int j = i + 1; j < listLen; j++) {

                if ( list.get(i).equals(list.get(j)) ) {
                    result.add(list.get(i));
                }
            }
        }

        System.out.println(Arrays.toString(result.toArray()));
    }

    /**
     * Remove duplicates and create a list
     * @param list
     */
    private void makeDeduplicatedList(List<String> list) {

        if ( list == null || list.size() == 0 ) {
            return;
        }

        Set<String> result = new HashSet<>();
        int listLen = list.size();
        for ( int i = 0; i <= listLen - 2; i++ ) {

            for ( int j = i + 1; j <= listLen - 1; j++ ) {

                if ( !list.get(i).equals(list.get(j)) ) {

                    result.add(list.get(i));
                }
            }
        }

        System.out.println(Arrays.toString(result.toArray()));
    }
}

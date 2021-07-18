package com.jay2u8809.codesamples.individual.algorithm.study;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class AddRandomTwoNumber {

    private final int[] sampleData1 = new int[]{2, 1, 3, 4, 1};
    private final int[] sampleData2 = new int[]{5, 0, 2, 7};

    @Test
    public void addRandomTwoNumberTest() {


        int[] result1 = solution(sampleData1);
        int[] result2 = solution(sampleData2);

        System.out.println(Arrays.toString(result1));
        System.out.println(Arrays.toString(result2));
    }


    private int[] solution(int[] numbers) {

        int[] answer = {};
        HashSet<Integer> hashSet = new HashSet<>();
        int numbersArrLen = numbers.length;
        for (int i = 0; i < numbersArrLen-1; i++) {

            for (int j = i+1; j < numbersArrLen; j++) {

                hashSet.add(numbers[i] + numbers[j]);
            }
        }

        answer = hashSet.stream()
                .sorted()
                .mapToInt(Integer::intValue)
                .toArray();
        /*
        List<Integer> intList = new ArrayList<>(hashSet);
        intList.sort(Integer::compareTo);

        answer = new int[intList.size()];

        int size=0;
        for(Integer temp : intList) {
            answer[size++] = temp;
        }
        */

        return answer;
    }
}

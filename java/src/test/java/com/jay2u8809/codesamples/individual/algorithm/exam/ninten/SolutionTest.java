package com.jay2u8809.codesamples.individual.algorithm.exam.ninten;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionTest {

    private Solution solution;

    @Test
    public void solutionATest() {
        this.solution = new Solution();
        String result;

        List<Integer> list1 = Arrays.stream("3 2 4 6 2 4 1 5 3 5".split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        result = this.solution.solutionA(3, list1);
        System.out.println(result);

        List<Integer> list2 = Arrays.stream("7 4 3 4 6 4 12 3 9 9 2 4 3 7 9 10 2 1 4 12".split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        result = this.solution.solutionA(5, list2);
        System.out.println(result);
    }

}
package com.jay2u8809.codesamples.individual.algorithm.exam.ninten;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SolutionMain {

    public static void main(String[] args) throws Exception {

        mainSolutionA();
    }

    public static void mainSolutionA() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Input Set Cnt : ");
        int setCnt = sc.nextInt();

        Solution solution = new Solution();

        for (int i = 0; i < setCnt ; i++) {
            System.out.print("Input Special Skill Cnt : ");
            int specialSkillCnt = sc.nextInt();
            sc.nextLine();

            System.out.print("Input Monster Group Cnt : ");
            String monsterGroupCnt = sc.nextLine();

            List<Integer> list = Arrays.stream(monsterGroupCnt.split(" ")).map(Integer::valueOf).collect(Collectors.toList());
            String result = solution.solutionA(specialSkillCnt, list);
            System.out.println(result);
        }


    }
}

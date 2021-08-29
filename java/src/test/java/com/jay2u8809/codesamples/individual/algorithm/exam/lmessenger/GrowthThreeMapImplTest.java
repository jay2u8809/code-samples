package com.jay2u8809.codesamples.individual.algorithm.exam.lmessenger;

import com.jay2u8809.codesamples.common.CommonExtends;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GrowthThreeMapImplTest extends CommonExtends {

    private GrowthThreeMapImpl growthThreeMapImpl;

    @BeforeEach
    public void init() {
        this.growthThreeMapImpl = new GrowthThreeMapImpl();
    }

    @Test
    public void solutionTest() {

        String[] testSample1 = {"add 5 3","add 1 2","get 5","evict","get 1","remove 5","exit"};
        String[] testSample2 = {"add 5 3","add 1 2","get 5","evict","exit", "get 1","remove 5"};
        String[] testSample3 = {"add 5 3","evict","exit", "add 1 2","get 5","get 1","remove 5"};
        String[] testSample4 = {"add 5 3","exit", "add 1 2","get 5","get 1","remove 5"};
        String[] testSample5 = {"add 5 3","add 1 2","get 3","get 1","remove 5", "exit"};
        String[] testSample6 = {"add 5 3","add 1 2","get 3","get 1","remove 5"};

        int[] test1 = growthThreeMapImpl.solution(testSample1);
        int[] test2 = growthThreeMapImpl.solution(testSample2);
        int[] test3 = growthThreeMapImpl.solution(testSample3);
        int[] test4 = growthThreeMapImpl.solution(testSample4);
        int[] test5 = growthThreeMapImpl.solution(testSample5);
        int[] test6 = growthThreeMapImpl.solution(testSample6);

        logger.info("Test1 Result : {}", test1);
        logger.info("Test2 Result : {}", test2);
        logger.info("Test3 Result : {}", test3);
        logger.info("Test4 Result : {}", test4);
        logger.info("Test5 Result : {}", test5);
        logger.info("Test6 Result : {}", test6);
    }

}
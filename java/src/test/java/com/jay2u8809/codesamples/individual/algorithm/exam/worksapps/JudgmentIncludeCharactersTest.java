package com.jay2u8809.codesamples.individual.algorithm.exam.worksapps;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JudgmentIncludeCharactersTest {

    private final List<String> sampleDatasForTest
            = new ArrayList<>(Arrays.asList(
                    "AABBCC CCBABA"                                 // True
                    , "ABCAB ABDBA"                                 // False
                    , "ABCABC ABCABC"                               // True
                ));

    private JudgmentIncludeCharacters judgmentIncludeCharactersForTest;    // Bean登録が必要

    @Test
    public void isContainCharactersTest() {

        judgmentIncludeCharactersForTest = new JudgmentIncludeCharacters();

        sampleDatasForTest.stream()
                .map(f -> f.split(" "))
                .forEach(f -> {

                    // Test Contents
                    boolean isContain = judgmentIncludeCharactersForTest.isContainCharacters(f[0], f[1]);
                    System.out.println("RESULT : " + isContain);
                });
    }

}

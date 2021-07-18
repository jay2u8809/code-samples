package com.jay2u8809.codesamples.individual.algorithm.study.datastructure;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class PerformanceOfListAndMapTest {

    private Logger logger = LoggerFactory.getLogger(PerformanceOfListAndMapTest.class);

    private long startTime;
    private long endTime;
    private long diffTime;

    @Test
    void testArrayAndLinkedList() {

        List<String> list1 = Arrays.asList("list1");
        List<String> list2 = Collections.singletonList("list2");

        // Size of collection Objects
        final int collectionLen = 100000;

        List<String> arrList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();

        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            arrList.add(String.valueOf(i));
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("ARR LIST ADD : {}", diffTime);        //2204985


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            linkedList.add(String.valueOf(i));
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("LINKED LIST ADD : {}", diffTime);         // 1694728


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            hashMap.put(i, String.valueOf(i+1));
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("HASH AMP ADD : {}", diffTime);         //


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            treeMap.put(i, String.valueOf(i+1));
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("TREE AMP ADD : {}", diffTime);         //


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            arrList.set(i, String.valueOf(collectionLen-1));
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("ARR LIST REPLACE: {}", diffTime);


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            linkedList.set(i, String.valueOf(collectionLen-1));
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("LINKED LIST REPLACE: {}", diffTime);


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            arrList.add(i, String.valueOf(i+1));
            arrList.remove(i+1);
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("ARR LIST ADD REMOVE: {}", diffTime);


        this.setZeroTimeField();
        startTime = System.nanoTime();
        for (int i = 0; i< collectionLen; i++) {
            linkedList.add(i, String.valueOf(i+1));
            linkedList.remove(i+1);
        }
        endTime = System.nanoTime();
        diffTime = endTime - startTime;
        logger.debug("LINKED LIST ADD REMOVE: {}", diffTime);


//        this.setZeroTimeField();
//        startTime = System.nanoTime();
//        for (int i = 0; i< collectionLen; i++) {
//            arrList.remove(i);
//        }
//        endTime = System.nanoTime();
//        diffTime = endTime - startTime;
//        logger.debug("ARR LIST REMOVE: {}", diffTime);
//
//
//        this.setZeroTimeField();
//        startTime = System.nanoTime();
//        for (int i = 0; i< collectionLen; i++) {
//            linkedList.remove(i);
//        }
//        endTime = System.nanoTime();
//        diffTime = endTime - startTime;
//        logger.debug("LINKED LIST REMOVE: {}", diffTime);

    }

    private void setZeroTimeField() {
        this.startTime = 0;
        this.endTime = 0;
        this.diffTime = 0;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.arraysumwiththreads;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class ArraySumWithThreads {
    private static final int THREAD_COUNT = 4; 
    private static final int ARRAY_SIZE = 1000000; 

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = (int) (Math.random() * 10 + 1);
        }
        
        
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);

        List<Future<Long>> futures = new ArrayList<>();


        int chunkSize = ARRAY_SIZE / THREAD_COUNT;
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int start = i * chunkSize;
            final int end = (i == THREAD_COUNT - 1) ? ARRAY_SIZE : (i + 1) * chunkSize;

            futures.add(executorService.submit(() -> {
                long sum = 0;
                for (int j = start; j < end; j++) {
                    sum += array[j];
                }
                return sum;
            }));
        }

        long totalSum = 0;
        for (Future<Long> future : futures) {
            totalSum += future.get(); 
        }

        System.out.println("Total sum of the array: " + totalSum);

        executorService.shutdown();
    }
}


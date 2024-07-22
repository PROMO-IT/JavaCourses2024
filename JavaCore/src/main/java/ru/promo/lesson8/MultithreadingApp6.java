package ru.promo.lesson8;

import java.util.concurrent.*;

public class MultithreadingApp6 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        SumCounter sumCounter1 = new SumCounter("Counter1");

        Future<Integer> result1 = executorService.submit(sumCounter1);
        Future<Integer> result2 = executorService.submit(sumCounter1);
        Future<Integer> result3 = executorService.submit(sumCounter1);


        System.out.println("result1 = " + result1.get());
        System.out.println("result2 = " + result2.get());
        System.out.println("result3 = " + result3.get());



        executorService.shutdown();
    }
}

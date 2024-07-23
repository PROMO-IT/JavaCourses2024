package ru.promo.lesson8;

import java.util.concurrent.*;

public class MultithreadingApp5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum = sum + i;
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": sum = " + sum);
                }
                return sum;
            }
        };

        Future<Integer> result1 = executorService.submit(callable);
        System.out.println("result1 = " + result1.get());

        Future<Integer> result2 = executorService.submit(callable);
        System.out.println("result2 = " + result2.get());

        Future<Integer> result3 = executorService.submit(callable);
        System.out.println("result3 = " + result3.get());

        executorService.shutdown();
    }
}

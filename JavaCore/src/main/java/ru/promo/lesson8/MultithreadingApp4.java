package ru.promo.lesson8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultithreadingApp4 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
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
                System.out.println(Thread.currentThread().getName() + ": sum = " + sum);
            }
        };

        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);

        runnable.run();

        executorService.shutdown();
    }
}

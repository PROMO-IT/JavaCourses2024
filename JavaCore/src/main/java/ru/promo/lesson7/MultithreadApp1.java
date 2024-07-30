package ru.promo.lesson7;

import java.util.concurrent.TimeUnit;

public class MultithreadApp1 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + i);
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        thread1.start();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                thread1.run();
            }
        };
        Thread thread2 = new Thread(runnable);
        thread2.start();

        thread1.join();
        thread2.join();


        thread1.run();
    }
}

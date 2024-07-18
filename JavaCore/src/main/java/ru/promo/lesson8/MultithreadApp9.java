package ru.promo.lesson8;

import java.util.concurrent.atomic.AtomicInteger;

public class MultithreadApp9 {
    private static AtomicInteger a = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    a.incrementAndGet();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    a.decrementAndGet();
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(a);
    }
}

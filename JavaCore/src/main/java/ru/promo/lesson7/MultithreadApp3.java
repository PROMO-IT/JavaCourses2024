package ru.promo.lesson7;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class MultithreadApp3 {
    private static volatile int a = 0;
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    lock.lock();
                    a++;
                    lock.unlock();
                }
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    lock.lock();
                    a--;
                    lock.unlock();
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

        Map
    }
}

package ru.promo.lesson7;

import java.util.concurrent.locks.ReentrantLock;

public class MultithreadApp2 {
    private static volatile int a = 0;
    public static void main(String[] args) {
//        Object lock = new Object();
        ReentrantLock lock = new ReentrantLock();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    lock.lock();
                    a++;
                    lock.unlock();
                }
            }
        };
        Thread thread2 = new Thread(runnable);
        thread2.start();

        while (true) {
            lock.lock();
                if (a % 2 == 0) {
                    {
                        System.out.println("a = " + a);
                    }
                }
            lock.unlock();
        }
    }
}

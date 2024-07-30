package ru.promo.lesson9;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
            System.out.println(Thread.currentThread() + " | " + name);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

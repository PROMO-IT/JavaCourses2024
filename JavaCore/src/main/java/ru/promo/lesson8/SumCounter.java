package ru.promo.lesson8;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SumCounter implements Callable<Integer> {
    private String name;
    private ThreadLocal<Integer> sum;

    public SumCounter(String name) {
        this.name = name;
        this.sum = new ThreadLocal<>();
    }

    @Override
    public Integer call() throws Exception {
        sum.set(0);
        for (int i = 0; i < 100; i++) {
            sum.set(sum.get() + i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName() + ":" + name + " | sum = " + sum.get());
        }
        return sum.get();
    }
}

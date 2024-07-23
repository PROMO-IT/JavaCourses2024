package ru.promo.lesson8;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SumCounter implements Callable<Integer> {
    private String name;
//    private ThreadLocal<Integer> sum;

    public SumCounter(String name) {
        this.name = name;
//        this.sum = new ThreadLocal<>();
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
//        sum.set(0);
        for (int i = 0; i < 100; i++) {
//            sum.set(sum.get() + i);
            sum += i;
//            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread() + ":" + name + " | sum = " + sum);
        }
//        return sum.get();
        return sum;
    }
}

package ru.promo.lesson9;

public class MultithreadApp8 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = Thread.ofVirtual().start(new Task("myTask1"));
        Thread thread2 = Thread.ofVirtual().start(new Task("myTask2"));
        Thread thread3 = Thread.ofVirtual().start(new Task("myTask3"));

        thread1.join();
        thread2.join();
        thread3.join();
    }
}

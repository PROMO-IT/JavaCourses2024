package ru.promo.lesson5;

import java.time.LocalDateTime;

public class Sprint implements Closable {
    private LocalDateTime openDate;
    private LocalDateTime closeDate;

    public Sprint() {
        openDate = LocalDateTime.now();
    }

    @Override
    public void close() {
        closeDate = LocalDateTime.now();
        System.out.println("Time closed " + closeDate);
    }
}

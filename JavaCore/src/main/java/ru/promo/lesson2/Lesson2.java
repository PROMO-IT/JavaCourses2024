package ru.promo.lesson2;

public class Lesson2 {

    public static void main(String[] args) {
        int i = 0;
        System.out.println("i = " + i);

        long l = 34L;
        System.out.println("l = " + l);

        byte b = 1;
        System.out.println("b = " + b);

        short s = 23;
        System.out.println("s = " + s);

        float f = 0.4f;
        System.out.println("f = " + f);

        double d = 5.4;
        System.out.println("d = " + d);

        boolean bool = true;
        System.out.println("bool = " + bool);

        char c = '\u0042';
        System.out.println("c = " + c);

        int a1 = 4;
        int a2 = 3;
        System.out.println(a1 * a2);
        System.out.println(a1 / a2);
        System.out.println(a1 + a2);
        System.out.println(a1 - a2);
        System.out.println(a1 % a2);

        System.out.println(1.0 - 0.7);

        int condition = 2;
        if (condition > 6) {
            System.out.println("condition 1");
        }

        int q = condition > 3 ? 4 : 5;
        System.out.println("q = " + q);

        int switchCondition = 2;
        switch (switchCondition) {
            case 0:
                System.out.println("case 0");
                break;
            case 1:
                System.out.println("case 1");
                break;
            case 2:
                System.out.println("case 2");
                break;
            default:
                System.out.println("default");
        }

        int switchExpression = 1;
        int result = switch (switchExpression) {
            case 0, 4 -> 10;
            case 1 -> {
                int a = 2 * 20;
                yield 2 * a;
            }
            case 2 -> 30;
            default -> 40;
        };

        System.out.println("result = " + result);

        int[] array = new int[4];
        array[1] = 3;
        System.out.println("array[1] = " + array[1]);

        int[] array2 = {1, 2, 3};
        System.out.println("array2[0] = " + array2[0]);

        int whileCondition = 3;
        while (whileCondition > 0) {
            System.out.println("whileCondition = " + whileCondition);
            whileCondition--;
        }

        int doWhile = 4;
        do {
            System.out.println("doWhile = " + doWhile);
            doWhile--;
        } while (doWhile > 1);

        for (int k = 0; k < 5; k++) {
            if (k == 2) {
                break;
            }
            System.out.println("k = " + k);
        }

        int[] array3 = {1, 2, 3, 4};
        for (int a3 : array3) {
            System.out.println("a3 = " + a3);
        }

        boolean h = true;
        boolean w = false;
        if (h && w) {
            System.out.println("condition!");
        } else {
            System.out.println("else condition");
        }

        int r = 4;
        int t = 2;
        System.out.println(4 & 2);
        System.out.println(4 >> 2);


        int k = 0;
        for (;;) {
            System.out.println("k = " + k);
            if (k == 5) {
                break;
            }
            k++;
        }
    }
}
